package al132.alchemistry.util;

import net.minecraft.item.ItemStack;

public class ItemStackUtil {
    public static ItemStack asSingleCopy(ItemStack stack) {
        stack = stack.copy();
        stack.setCount(1);
        return stack;
    }
}
