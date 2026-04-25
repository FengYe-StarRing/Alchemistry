package al132.alchemistry.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.NotNull;

public class OnlyOutputItemHandler extends RangedWrapper {
    public OnlyOutputItemHandler(IItemHandlerModifiable compose,int minSlot,int maxSlotExclusive) {
        super(compose,minSlot,maxSlotExclusive);
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot,@NotNull ItemStack stack,boolean simulate) {
        return stack.copy();
    }
}
