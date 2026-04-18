package al132.alchemistry.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.NotNull;

public class OnlyInputItemHandler extends RangedWrapper {
    public OnlyInputItemHandler(IItemHandlerModifiable compose,int minSlot,int maxSlotExclusive) {
        super(compose,minSlot,maxSlotExclusive);
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot,int amount,boolean simulate) {
        return ItemStack.EMPTY;
    }
}
