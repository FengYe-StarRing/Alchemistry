package al132.alchemistry.tileentity;

import al132.alchemistry.energy.AlchemistryEnergyStorage;
import al132.alchemistry.handler.OnlyInputFluidHandler;
import al132.alchemistry.handler.OnlyInputItemHandler;
import al132.alchemistry.handler.OnlyOutputEnergyStorageHandler;
import al132.alchemistry.recipe.ModRecipes;
import al132.alchemistry.recipe.SolidFuelCellRecipe;
import al132.alchemistry.util.EnergyUtil;
import al132.alchemistry.util.TickTimer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntitySolidFuelCell extends TileEntityMachine {
    public final ItemStackHandler fuel = new ItemStackHandler();
    public final TickTimer timer = new TickTimer();
    public int outputPower = 0;
    public int energy = 0;
    public int tick = 0;

    public TileEntitySolidFuelCell() {
        super(new FluidTank[]{new FluidTank(1000)},new AlchemistryEnergyStorage(Integer.MAX_VALUE));
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        ItemStack fuelStack = fuel.getStackInSlot(0);
        FluidTank oxygenTank = fluidTanks[0];
        // 运行配方
        if(!fuelStack.isEmpty() && oxygenTank.getFluid() != null && tick == 0 && energy == 0) {
            // 搜索配方
            for(SolidFuelCellRecipe recipe : ModRecipes.solidFuelCellRecipes) {
                if(recipe.match(fuelStack,oxygenTank.getFluid())) {
                    // 检查运行条件是否满足
                    if(recipe.canApply(fuelStack,oxygenTank.getFluid())) {
                        fuelStack.shrink(recipe.fuel.getCount());
                        oxygenTank.drain(recipe.oxygen,true);
                        energy = recipe.energy;
                        tick = recipe.tick;
                    }
                    break; // 只匹配第一个可用配方
                }
            }
        }
        // 生产能量
        if(tick != 0 && energy != 0) {
            energyStorage.receiveEnergy(energy,false);
            if(timer.hasTimePassed(tick)) {
                tick = 0;
                energy = 0;
            }
        }
        // 向周围主动输出缓存内的能量
        outputPower = 0;
        if(energyStorage.getEnergyStored() > 0) {
            for(EnumFacing facing : EnumFacing.values()) {
                TileEntity tile = world.getTileEntity(pos.offset(facing));
                if(tile != null) {
                    outputPower += EnergyUtil.transfer(energyStorage,tile,facing.getOpposite(),false);
                }
            }
        }
        markDirty();
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(fuel.getStackInSlot(0).isEmpty() && tick == 0 && energy == 0) {
                return true;
            }
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if(tick == 0 && energy == 0) {
                return true;
            }
        }
        return super.hasCapability(capability,facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new OnlyOutputEnergyStorageHandler(energyStorage));
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(fuel.getStackInSlot(0).isEmpty() && tick == 0 && energy == 0) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new OnlyInputItemHandler(fuel,0,1));
            }
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if(tick == 0 && energy == 0) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new OnlyInputFluidHandler(fluidTanks[0]));
            }
        }
        return super.getCapability(capability,facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        fuel.deserializeNBT(compound.getCompoundTag("Fuel"));
        timer.deserializeNBT(compound.getCompoundTag("Timer"));
        outputPower = compound.getInteger("OutputPower");
        energy = compound.getInteger("Energy");
        tick = compound.getInteger("Tick");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Fuel",fuel.serializeNBT());
        compound.setTag("Timer",timer.serializeNBT());
        compound.setInteger("OutputPower",outputPower);
        compound.setInteger("Energy",energy);
        compound.setInteger("Tick",tick);
        return compound;
    }
}
