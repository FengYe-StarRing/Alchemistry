package al132.alchemistry.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class DualItemHandler implements IItemHandler {
    private final ItemStackHandler input,output;
    private final int inputSlot,outputSlot;

    public DualItemHandler(ItemStackHandler input,int inputSlot,ItemStackHandler output,int outputSlot) {
        this.input = input;
        this.inputSlot = inputSlot;
        this.output = output;
        this.outputSlot = outputSlot;
    }

    @Override
    public int getSlots() {
        return 2;
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot) {
        if(slot == 0) return input.getStackInSlot(inputSlot);
        if(slot == 1) return output.getStackInSlot(outputSlot);
        return ItemStack.EMPTY;
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot,@NotNull ItemStack stack,boolean simulate) {
        if(slot == 0) return input.insertItem(inputSlot,stack,simulate);
        return stack;
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot,int amount,boolean simulate) {
        if(slot == 1) return output.extractItem(outputSlot,amount,simulate);
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        if(slot == 0) return input.getSlotLimit(inputSlot);
        if(slot == 1) return output.getSlotLimit(outputSlot);
        return 0;
    }
}
