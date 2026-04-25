package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityDistillationChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerDistillationChamber extends ContainerMachine<TileEntityDistillationChamber> {
    public ContainerDistillationChamber(EntityPlayer player,TileEntity tile) {
        super(player,tile);
        addSlotToContainer(tileEntity.inputItemHandler,0,0,2);
        addSlotToContainer(tileEntity.inputItemHandler,1,0,8);
        addSlotToContainer(tileEntity.outputItemHandler,0,2,2);
        addSlotToContainer(tileEntity.outputItemHandler,1,2,8);
    }
}
