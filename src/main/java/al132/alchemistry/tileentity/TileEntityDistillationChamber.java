package al132.alchemistry.tileentity;

import al132.alchemistry.energy.AlchemistryEnergyStorage;
import al132.alchemistry.handler.DualFluidHandler;
import al132.alchemistry.handler.DualItemHandler;
import al132.alchemistry.handler.OnlyInputEnergyStorageHandler;
import al132.alchemistry.recipe.DistillationChamberRecipe;
import al132.alchemistry.recipe.ModRecipes;
import al132.alchemistry.util.FluidTankUtil;
import al132.alchemistry.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
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

public class TileEntityDistillationChamber extends TileEntityMachine {
    public TileEntityDistillationChamber() {
        super(new ItemStackHandler(3),new ItemStackHandler(3),new FluidTank[]{new FluidTank(1000),new FluidTank(1000)},new AlchemistryEnergyStorage(1000));
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        ItemStack inputItemStack = inputItemHandler.getStackInSlot(1);
        ItemStack outputItemStack = outputItemHandler.getStackInSlot(1);
        FluidTank inputFluidTank = fluidTanks[0];
        FluidTank outputFluidTank = fluidTanks[1];
        // 转移流体
        if(!inputItemHandler.getStackInSlot(0).isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(inputItemHandler.getStackInSlot(0),outputItemHandler.getStackInSlot(0),inputFluidTank);
            inputItemHandler.setStackInSlot(0,stacks[0]);
            outputItemHandler.setStackInSlot(0,stacks[1]);
        }
        if(!inputItemHandler.getStackInSlot(2).isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(outputFluidTank,inputItemHandler.getStackInSlot(2),outputItemHandler.getStackInSlot(2));
            inputItemHandler.setStackInSlot(2,stacks[0]);
            outputItemHandler.setStackInSlot(2,stacks[1]);
        }
        // 运行配方
        if(!inputItemStack.isEmpty() || inputFluidTank.getFluidAmount() != 0) {
            timer.reset();
            // 搜索配方
            for(DistillationChamberRecipe recipe : ModRecipes.distillationChamberRecipes) {
                if(recipe.match(inputFluidTank.getFluid(),inputItemStack)) {
                    // 检查运行条件是否满足
                    if(recipe.canApply(energyStorage,inputFluidTank,inputItemStack,outputFluidTank,outputItemStack)) {
                        timer.undoReset();
                        energyStorage.extractEnergy(recipe.energy,false);
                        if(!timer.hasTimePassed(recipe.tick)) {
                            break;
                        }
                        inputFluidTank.drain(recipe.fluidInput,true);
                        inputItemStack.shrink(recipe.itemInput.getCount());
                        outputFluidTank.fill(recipe.fluidOutput,true);
                        if(outputItemStack.isEmpty()) {
                            outputItemHandler.setStackInSlot(1,recipe.itemOutput.copy());
                        } else {
                            outputItemStack.grow(recipe.itemOutput.getCount());
                        }
                    }
                    break; // 只匹配第一个可用配方
                }
            }
        }
        // 主动向外输出产物
        for(EnumFacing facing : EnumFacing.values()) {
            TileEntity tile = world.getTileEntity(pos.offset(facing));
            if(tile != null) {
                FluidTankUtil.transfer(this,tile,null,facing.getOpposite());
                ItemStackUtil.transfer(this,tile,null,facing.getOpposite());
            }
        }
        markDirty();
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability,facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new DualFluidHandler(fluidTanks[0],fluidTanks[1]));
        }
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new OnlyInputEnergyStorageHandler(energyStorage));
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new DualItemHandler(inputItemHandler,1,outputItemHandler,1));
        }
        return super.getCapability(capability,facing);
    }
}
