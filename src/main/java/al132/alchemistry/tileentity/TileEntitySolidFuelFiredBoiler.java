package al132.alchemistry.tileentity;

import al132.alchemistry.handler.DualFluidHandler;
import al132.alchemistry.handler.OnlyInputItemHandler;
import al132.alchemistry.recipe.ModRecipes;
import al132.alchemistry.recipe.SolidFuelFiredBoilerRecipe;
import al132.alchemistry.util.FluidTankUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntitySolidFuelFiredBoiler extends TileEntityMachine {
    public int burnTime = 0;
    public int maxBurnTime = 1;

    public TileEntitySolidFuelFiredBoiler() {
        super(new ItemStackHandler(3),new ItemStackHandler(2),new FluidTank[]{new FluidTank(1000),new FluidTank(1000)});
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        ItemStack fuelItemStack = inputItemHandler.getStackInSlot(0);
        ItemStack waterBucketItemStack = inputItemHandler.getStackInSlot(1);
        ItemStack emptySteamBucketItemStack = inputItemHandler.getStackInSlot(2);
        ItemStack emptyWaterBucketItemStack = outputItemHandler.getStackInSlot(0);
        ItemStack steamBucketItemStack = outputItemHandler.getStackInSlot(1);
        FluidTank waterTank = fluidTanks[0];
        FluidTank steamTank = fluidTanks[1];
        // 将水桶中的水转移到容器内并输出空桶
        if(!waterBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(waterBucketItemStack,emptyWaterBucketItemStack,waterTank);
            inputItemHandler.setStackInSlot(1,stacks[0]);
            outputItemHandler.setStackInSlot(0,stacks[1]);
        }
        // 将容器内的蒸汽转移到桶内并输出
        if(!emptySteamBucketItemStack.isEmpty()) {
            ItemStack[] stacks = FluidTankUtil.transfer(steamTank,emptySteamBucketItemStack,steamBucketItemStack);
            inputItemHandler.setStackInSlot(2,stacks[0]);
            outputItemHandler.setStackInSlot(1,stacks[1]);
        }
        // 运行配方
        if(waterTank.getFluid() != null) {
            // 搜索配方
            for (SolidFuelFiredBoilerRecipe recipe : ModRecipes.solidFuelFiredBoilerRecipes) {
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
        if(!fuelItemStack.isEmpty() && burnTime < maxBurnTime) {
            int fuel = TileEntityFurnace.getItemBurnTime(fuelItemStack) / 80;
            if(fuel > 0) {
                fuelItemStack.shrink(1);
                burnTime += fuel;
                maxBurnTime = fuel;
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
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new OnlyInputItemHandler(inputItemHandler,0,1));
        }
        return super.getCapability(capability,facing);
    }
}
