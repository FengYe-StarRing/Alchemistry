package al132.alchemistry.tileentity;

import al132.alchemistry.energy.AlchemistryEnergyStorage;
import al132.alchemistry.handler.OnlyInputEnergyStorageHandler;
import al132.alchemistry.handler.OnlyOutputItemHandler;
import al132.alchemistry.handler.ThreeFluidHandler;
import al132.alchemistry.recipe.ModRecipes;
import al132.alchemistry.recipe.RefineryChamberRecipe;
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

public class TileEntityRefineryChamber extends TileEntityMachine {
    public TileEntityRefineryChamber() {
        super(new ItemStackHandler(6),new ItemStackHandler(7),new FluidTank[]{new FluidTank(2000),new FluidTank(2000),new FluidTank(1000),new FluidTank(1000),new FluidTank(1000),new FluidTank(1000)},new AlchemistryEnergyStorage(1000));
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        ItemStack oilBucketItemStack = inputItemHandler.getStackInSlot(0);
        ItemStack crackedGasBucketItemStack = inputItemHandler.getStackInSlot(1);
        ItemStack emptyPetroleumGasBucketItemStack = inputItemHandler.getStackInSlot(2);
        ItemStack emptyLightFractionBucketItemStack = inputItemHandler.getStackInSlot(3);
        ItemStack emptyMiddleDistillateBucketItemStack = inputItemHandler.getStackInSlot(4);
        ItemStack emptyHeavyFractionBucketItemStack = inputItemHandler.getStackInSlot(5);
        ItemStack emptyOilBucketItemStack = outputItemHandler.getStackInSlot(0);
        ItemStack emptyCrackedGasBucketItemStack = outputItemHandler.getStackInSlot(1);
        ItemStack petroleumGasBucketItemStack = outputItemHandler.getStackInSlot(2);
        ItemStack lightFractionBucketItemStack = outputItemHandler.getStackInSlot(3);
        ItemStack middleDistillateBucketItemStack = outputItemHandler.getStackInSlot(4);
        ItemStack heavyFractionBucketItemStack = outputItemHandler.getStackInSlot(5);
        ItemStack oilResidueItemStack = outputItemHandler.getStackInSlot(6);
        FluidTank oilTank = fluidTanks[0];
        FluidTank crackedGasTank = fluidTanks[1];
        FluidTank petroleumGasTank = fluidTanks[2];
        FluidTank lightFractionTank = fluidTanks[3];
        FluidTank middleDistillateTank = fluidTanks[4];
        FluidTank heavyFractionTank = fluidTanks[5];
        // 转移流体
        if(!oilBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(oilBucketItemStack,emptyOilBucketItemStack,oilTank);
            inputItemHandler.setStackInSlot(0,stacks[0]);
            outputItemHandler.setStackInSlot(0,stacks[1]);
        }
        if(!crackedGasBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(crackedGasBucketItemStack,emptyCrackedGasBucketItemStack,crackedGasTank);
            inputItemHandler.setStackInSlot(1,stacks[0]);
            outputItemHandler.setStackInSlot(1,stacks[1]);
        }
        if(!emptyPetroleumGasBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(petroleumGasTank,emptyPetroleumGasBucketItemStack,petroleumGasBucketItemStack);
            inputItemHandler.setStackInSlot(2,stacks[0]);
            outputItemHandler.setStackInSlot(2,stacks[1]);
        }
        if(!emptyLightFractionBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(lightFractionTank,emptyLightFractionBucketItemStack,lightFractionBucketItemStack);
            inputItemHandler.setStackInSlot(3,stacks[0]);
            outputItemHandler.setStackInSlot(3,stacks[1]);
        }
        if(!emptyMiddleDistillateBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(middleDistillateTank,emptyMiddleDistillateBucketItemStack,middleDistillateBucketItemStack);
            inputItemHandler.setStackInSlot(4,stacks[0]);
            outputItemHandler.setStackInSlot(4,stacks[1]);
        }
        if(!emptyHeavyFractionBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(heavyFractionTank,emptyHeavyFractionBucketItemStack,heavyFractionBucketItemStack);
            inputItemHandler.setStackInSlot(5,stacks[0]);
            outputItemHandler.setStackInSlot(5,stacks[1]);
        }
        // 运行配方
        if(oilTank.getFluid() != null) {
            timer.reset();
            // 搜索配方
            for(RefineryChamberRecipe recipe : ModRecipes.refineryChamberRecipes) {
                if(recipe.match(oilTank.getFluid(),crackedGasTank.getFluid())) {
                    // 检查运行条件是否满足
                    if(recipe.canApply(energyStorage,oilTank,crackedGasTank,petroleumGasTank,lightFractionTank,middleDistillateTank,heavyFractionTank,oilResidueItemStack)) {
                        timer.undoReset();
                        energyStorage.extractEnergy(recipe.energy,false);
                        if(!timer.hasTimePassed(recipe.tick)) {
                            break;
                        }
                        oilTank.drain(recipe.input1,true);
                        crackedGasTank.drain(recipe.input2,true);
                        petroleumGasTank.fill(recipe.petroleumGas,true);
                        lightFractionTank.fill(recipe.lightFraction,true);
                        middleDistillateTank.fill(recipe.middleDistillate,true);
                        heavyFractionTank.fill(recipe.heavyFraction,true);
                        if(oilResidueItemStack.isEmpty()) {
                            outputItemHandler.setStackInSlot(6,recipe.oilResidue.copy());
                        } else {
                            oilResidueItemStack.grow(recipe.oilResidue.getCount());
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
            FluidTank outputTank = new FluidTank(0);
            for(int i = 2;i < fluidTanks.length;i++) {
                if(fluidTanks[i].getFluidAmount() > 0) {
                    outputTank = fluidTanks[i];
                    break;
                }
            }
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new ThreeFluidHandler(fluidTanks[0],fluidTanks[1],outputTank));
        }
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new OnlyInputEnergyStorageHandler(energyStorage));
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new OnlyOutputItemHandler(outputItemHandler,6,7));
        }
        return super.getCapability(capability,facing);
    }
}
