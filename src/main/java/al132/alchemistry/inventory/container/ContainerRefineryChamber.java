package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityRefineryChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerRefineryChamber extends ContainerLargeGuiMachine<TileEntityRefineryChamber> {
    public ContainerRefineryChamber(EntityPlayer player,TileEntity tile) {
        super(player,tile);
        addSlotToContainer(tileEntity.inputItemHandler,0,3,1);
        addSlotToContainer(tileEntity.inputItemHandler,1,3,2);
        addSlotToContainer(tileEntity.inputItemHandler,2,3,4);
        addSlotToContainer(tileEntity.inputItemHandler,3,3,5);
        addSlotToContainer(tileEntity.inputItemHandler,4,3,6);
        addSlotToContainer(tileEntity.inputItemHandler,5,3,7);
        addSlotToContainer(tileEntity.outputItemHandler,0,5,1);
        addSlotToContainer(tileEntity.outputItemHandler,1,5,2);
        addSlotToContainer(tileEntity.outputItemHandler,2,5,4);
        addSlotToContainer(tileEntity.outputItemHandler,3,5,5);
        addSlotToContainer(tileEntity.outputItemHandler,4,5,6);
        addSlotToContainer(tileEntity.outputItemHandler,5,5,7);
        addSlotToContainer(tileEntity.outputItemHandler,6,1,8);
    }
}
