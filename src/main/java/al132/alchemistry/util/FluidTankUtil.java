package al132.alchemistry.util;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

public class FluidTankUtil {
    /**
     * 把物品容器中的流体转移到流体容器中
     * @param input 物品容器
     * @param output 空物品容器
     * @param tank 流体容器
     * @return 转移后的物品容器与空物品容器
     */
    public static ItemStack[] transfer(ItemStack input,ItemStack output,FluidTank tank) {
        FluidStack fluidStack = tank.getFluid() == null ? null : tank.getFluid().copy();
        input = input.copy();
        output = output.copy();
        if(!canFullyEmptyContainer(input,tank)) {
            return new ItemStack[]{input,output};
        }
        FluidActionResult result = FluidUtil.tryEmptyContainer(input,tank,Integer.MAX_VALUE,null,true);
        if(!result.isSuccess()) {
            return new ItemStack[]{input,output};
        }
        ItemStack resultStack = result.getResult();
        FluidStack resultFluidStack = FluidUtil.getFluidContained(resultStack);
        if(resultFluidStack == null || resultFluidStack.amount == 0) {
            if(output.isEmpty()) {
                input.shrink(1);
                return new ItemStack[]{input,resultStack};
            } else if(ItemHandlerHelper.canItemStacksStack(output,resultStack)) {
                input.shrink(1);
                output.grow(resultStack.getCount());
                return new ItemStack[]{input,output};
            }
        }
        tank.setFluid(fluidStack);
        return new ItemStack[]{input,output};
    }

    /**
     * 判断物品容器中的流体是否可以完整转移到流体容器中
     * @param container 物品容器
     * @param tank 流体容器
     * @return 为true时可以完整转移
     */
    public static boolean canFullyEmptyContainer(ItemStack container,FluidTank tank) {
        IFluidHandlerItem handler = FluidUtil.getFluidHandler(ItemStackUtil.asSingleCopy(container));
        if(handler == null) {
            return false;
        }
        FluidStack drained = handler.drain(Integer.MAX_VALUE,false);
        if(drained != null) {
            return drained.amount == tank.fill(drained,false);
        }
        return false;
    }

    /**
     * 把流体容器中的流体完整填满到物品容器中
     * @param tank 流体容器
     * @param input 空物品容器
     * @param output 物品容器
     * @return 转移后的物品容器与空物品容器
     */
    public static ItemStack[] transfer(FluidTank tank,ItemStack input,ItemStack output) {
        FluidStack fluidStack = tank.getFluid() == null ? null : tank.getFluid().copy();
        input = input.copy();
        output = output.copy();
        if(!canFullyFillContainer(input,tank)) {
            return new ItemStack[]{input,output};
        }
        FluidActionResult result = FluidUtil.tryFillContainer(input,tank,Integer.MAX_VALUE,null,true);
        if(!result.isSuccess()) {
            return new ItemStack[]{input,output};
        }
        ItemStack resultStack = result.getResult();
        if(output.isEmpty()) {
            input.shrink(1);
            return new ItemStack[]{input,resultStack};
        } else if(ItemHandlerHelper.canItemStacksStack(output,resultStack)) {
            input.shrink(1);
            output.grow(resultStack.getCount());
            return new ItemStack[]{input,output};
        }
        tank.setFluid(fluidStack);
        return new ItemStack[]{input,output};
    }

    /**
     * 判断流体容器中的流体是否可以完整填满物品容器中
     * @param container 物品容器
     * @param tank 流体容器
     * @return 为true时可以完整填满
     */
    public static boolean canFullyFillContainer(ItemStack container,FluidTank tank) {
        IFluidHandlerItem handler = FluidUtil.getFluidHandler(ItemStackUtil.asSingleCopy(container));
        FluidStack fluidStack = tank.getFluid();
        if(handler == null || fluidStack == null) {
            return false;
        }
        fluidStack = fluidStack.copy();
        fluidStack.amount = Integer.MAX_VALUE;
        int filled = handler.fill(fluidStack,false);
        return tank.getFluid().amount >= filled;
    }

    /**
     * 将源方块实体中的流体转移到目标方块实体中
     * @param tile 源方块实体
     * @param targetTile 目标方块实体
     * @param facing 源方块的交互面
     * @param targetFacing 目标方块的交互面
     */
    public static void transfer(TileEntity tile,TileEntity targetTile,EnumFacing facing,EnumFacing targetFacing) {
        IFluidHandler handler1 = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,facing);
        IFluidHandler handler2 = targetTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,targetFacing);
        if(handler1 != null && handler2 != null) {
            FluidUtil.tryFluidTransfer(handler2,handler1,Integer.MAX_VALUE,true);
        }
    }
}
