package al132.alchemistry.util;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemStackUtil {
    public static ItemStack asSingleCopy(ItemStack stack) {
        stack = stack.copy();
        stack.setCount(1);
        return stack;
    }

    // 检查stack2是否可以被合并进stack1
    public static boolean canMerge(ItemStack stack1,ItemStack stack2) {
        return (stack1.isEmpty() || stack2.isEmpty() || ItemHandlerHelper.canItemStacksStack(stack1,stack2)) && stack1.getMaxStackSize() - stack1.getCount() >= stack2.getCount();
    }

    /**
     * 转移物品栏中的全部物品到另一个物品栏中
     * @param source 提供物品的物品栏
     * @param target 接收物品的物品栏
     */
    public static void transfer(IItemHandler source,IItemHandler target) {
        for(int i = 0;i < source.getSlots();i++) {
            ItemStack sourceExtractItem = source.extractItem(i,Integer.MAX_VALUE,true);
            if(ItemHandlerHelper.insertItem(target,sourceExtractItem,true).isEmpty()) {
                ItemHandlerHelper.insertItem(target,source.extractItem(i,Integer.MAX_VALUE,false),false);
            }
        }
    }

    /**
     * 将源方块实体中的物品栏内的全部物品转移到目标方块实体的物品栏中
     * @param tile 源方块实体
     * @param targetTile 目标方块实体
     * @param facing 源方块的交互面
     * @param targetFacing 目标方块的交互面
     */
    public static void transfer(TileEntity tile,TileEntity targetTile,EnumFacing facing,EnumFacing targetFacing) {
        IItemHandler source = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,facing);
        IItemHandler target = targetTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,targetFacing);
        if(source != null && target != null) {
            transfer(source,target);
        }
    }
}
