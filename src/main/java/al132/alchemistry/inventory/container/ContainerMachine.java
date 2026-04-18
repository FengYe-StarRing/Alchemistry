package al132.alchemistry.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMachine<T extends TileEntity> extends Container {
    public final T tileEntity;

    public ContainerMachine(EntityPlayer player,TileEntity tile) {
        // 玩家背包的物品槽
        for(int i = 0;i < 9;i++) {
            addSlotToContainer(new Slot(player.inventory,i,8 + i * 18,142));
        }
        for(int i = 0;i < 3;i++) {
            for (int j = 0;j < 9;j++) {
                addSlotToContainer(new Slot(player.inventory,(i + 1) * 9 + j,8 + j * 18,84 + i * 18));
            }
        }
        tileEntity = (T)tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn,int index) {
        return ItemStack.EMPTY;
    }

    public void addSlotToContainer(IItemHandler itemHandler,int index,int line,int row) {
        addSlotToContainer(new SlotItemHandler(itemHandler,index,8 + line * 18,17 + row * 18));
    }
}
