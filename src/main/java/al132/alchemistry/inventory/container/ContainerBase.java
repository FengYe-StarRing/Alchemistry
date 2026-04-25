package al132.alchemistry.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBase extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    public void addSlotToContainer(IItemHandler itemHandler,int index,int row,int column) {
        addSlotToContainer(new SlotItemHandler(itemHandler,index,8 + column * 18,17 + row * 18));
    }
}
