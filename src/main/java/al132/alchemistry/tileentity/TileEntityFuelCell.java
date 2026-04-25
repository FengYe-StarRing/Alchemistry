package al132.alchemistry.tileentity;

import al132.alchemistry.energy.AlchemistryEnergyStorage;
import al132.alchemistry.handler.OnlyInputMultiFluidHandler;
import al132.alchemistry.handler.OnlyOutputEnergyStorageHandler;
import al132.alchemistry.recipe.FuelCellRecipe;
import al132.alchemistry.recipe.ModRecipes;
import al132.alchemistry.util.EnergyUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityFuelCell extends TileEntityMachine {
    public int outputPower;

    public TileEntityFuelCell() {
        super(new FluidTank[]{new FluidTank(1),new FluidTank(1)},new AlchemistryEnergyStorage(Integer.MAX_VALUE));
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        FluidTank fuelTank = fluidTanks[0];
        FluidTank oxygenTank = fluidTanks[1];
        // 运行配方
        if(fuelTank.getFluid() != null && oxygenTank.getFluid() != null && energyStorage.getEnergyStored() == 0) {
            // 搜索配方
            for(FuelCellRecipe recipe : ModRecipes.fuelCellRecipes) {
                if(recipe.match(fuelTank.getFluid(),oxygenTank.getFluid()) || recipe.match(oxygenTank.getFluid(),fuelTank.getFluid())) {
                    // 检查运行条件是否满足
                    if(recipe.canApply(fuelTank.getFluid(),oxygenTank.getFluid(),energyStorage)) {
                        fuelTank.drain(recipe.input1,true);
                        oxygenTank.drain(recipe.input2,true);
                        energyStorage.receiveEnergy(recipe.energy,false);
                        break;
                    }
                    // 防止流体输入进错误的位置
                    if(recipe.canApply(oxygenTank.getFluid(),fuelTank.getFluid(),energyStorage)) {
                        oxygenTank.drain(recipe.input1,true);
                        fuelTank.drain(recipe.input2,true);
                        energyStorage.receiveEnergy(recipe.energy,false);
                        break;
                    }
                    break; // 只匹配第一个可用配方
                }
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
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        outputPower = compound.getInteger("OutputPower");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("OutputPower",outputPower);
        return compound;
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability,facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new OnlyInputMultiFluidHandler(fluidTanks));
        }
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new OnlyOutputEnergyStorageHandler(energyStorage));
        }
        return super.getCapability(capability,facing);
    }
}
