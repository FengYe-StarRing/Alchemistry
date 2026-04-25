package al132.alchemistry.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemStackUtil {
    public static ItemStack asSingleCopy(ItemStack stack) {
        stack = stack.copy();
        stack.setCount(1);
        return stack;
    }

    public static boolean canMerge(ItemStack stack1,ItemStack stack2) {
        return (stack1.isEmpty() || stack2.isEmpty() || ItemHandlerHelper.canItemStacksStack(stack1,stack2)) && stack1.getMaxStackSize() - stack1.getCount() >= stack2.getCount();
    }
}
