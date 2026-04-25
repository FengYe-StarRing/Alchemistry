package al132.alchemistry.tileentity;

import al132.alchemistry.handler.ThreeFluidHandler;
import al132.alchemistry.recipe.FluidFuelBoilerRecipe;
import al132.alchemistry.recipe.ModRecipes;
import al132.alchemistry.recipe.SolidFuelFiredBoilerRecipe;
import al132.alchemistry.util.FluidTankUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityFluidFuelBoiler extends TileEntityMachine {
    public int burnTime = 0;
    public int maxBurnTime = 1;

    public TileEntityFluidFuelBoiler() {
        super(new ItemStackHandler(3),new ItemStackHandler(3),new FluidTank[]{new FluidTank(1000),new FluidTank(1000),new FluidTank(1000)});
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        ItemStack fuelBucketItemStack = inputItemHandler.getStackInSlot(0);
        ItemStack waterBucketItemStack = inputItemHandler.getStackInSlot(1);
        ItemStack steamBucketItemStack = inputItemHandler.getStackInSlot(2);
        ItemStack emptyFuelBucketItemStack = outputItemHandler.getStackInSlot(0);
        ItemStack emptyWaterBucketItemStack = outputItemHandler.getStackInSlot(1);
        ItemStack emptySteamBucketItemStack = outputItemHandler.getStackInSlot(2);
        FluidTank fuelTank = fluidTanks[0];
        FluidTank waterTank = fluidTanks[1];
        FluidTank steamTank = fluidTanks[2];
        // 转移流体
        if(!fuelBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(fuelBucketItemStack,emptyFuelBucketItemStack,fuelTank);
            inputItemHandler.setStackInSlot(0,stacks[0]);
            outputItemHandler.setStackInSlot(0,stacks[1]);
        }
        if(!waterBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(waterBucketItemStack,emptyWaterBucketItemStack,waterTank);
            inputItemHandler.setStackInSlot(1,stacks[0]);
            outputItemHandler.setStackInSlot(1,stacks[1]);
        }
        if(!steamBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(steamBucketItemStack,emptySteamBucketItemStack,steamTank);
            inputItemHandler.setStackInSlot(2,stacks[0]);
            outputItemHandler.setStackInSlot(2,stacks[1]);
        }
        // 运行配方
        if(waterTank.getFluid() != null) {
            // 搜索配方
            for(SolidFuelFiredBoilerRecipe recipe : ModRecipes.solidFuelFiredBoilerRecipes) {
                if(recipe.match(waterTank.getFluid())) {
                    // 检查运行条件是否满足
                    if(recipe.canApply(burnTime,waterTank.getFluid(),steamTank)) {
                        burnTime -= recipe.fuel;
                        waterTank.drain(recipe.input,true);
                        steamTank.fill(recipe.output,true);
                    }
                    break; // 只匹配第一个可用配方
                }
            }
        }
        // 添加燃料
        if(fuelTank.getFluid() != null && burnTime < maxBurnTime) {
            for(FluidFuelBoilerRecipe recipe : ModRecipes.fluidFuelBoilerRecipes) {
                if(recipe.match(fuelTank.getFluid())) {
                    if(recipe.canApply(fuelTank.getFluid())) {
                        fuelTank.drain(recipe.input,true);
                        burnTime += recipe.fuel;
                        maxBurnTime = recipe.fuel;
                    }
                    break;
                }
            }
        }
        // 主动向外输出蒸汽
        for(EnumFacing facing : EnumFacing.values()) {
            TileEntity target = world.getTileEntity(pos.offset(facing));
            if(target != null) {
                FluidTankUtil.transfer(this,target,null,facing.getOpposite());
            }
        }
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        burnTime = compound.getInteger("BurnTime");
        maxBurnTime = compound.getInteger("MaxBurnTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime",burnTime);
        compound.setInteger("MaxBurnTime",maxBurnTime);
        return compound;
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability,facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new ThreeFluidHandler(fluidTanks[0],fluidTanks[1],fluidTanks[2]));
        }
        return super.getCapability(capability,facing);
    }
}
