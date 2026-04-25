package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityFluidFuelBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerFluidFuelBoiler extends ContainerMachine<TileEntityFluidFuelBoiler> {
    public ContainerFluidFuelBoiler(EntityPlayer player,TileEntity tile) {
        super(player,tile);
        addSlotToContainer(tileEntity.inputItemHandler,0,0,2);
        addSlotToContainer(tileEntity.inputItemHandler,1,0,5);
        addSlotToContainer(tileEntity.inputItemHandler,2,0,8);
        addSlotToContainer(tileEntity.outputItemHandler,0,2,2);
        addSlotToContainer(tileEntity.outputItemHandler,1,2,5);
        addSlotToContainer(tileEntity.outputItemHandler,2,2,8);
    }
}
