package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntitySolidFuelFiredBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerSolidFuelFiredBoiler extends ContainerMachine<TileEntitySolidFuelFiredBoiler> {
    public ContainerSolidFuelFiredBoiler(EntityPlayer player,TileEntity tile) {
        super(player,tile);
        // 添加物品槽
        addSlotToContainer(tileEntity.inputItemHandler,0,2,2);
        addSlotToContainer(tileEntity.inputItemHandler,1,5,0);
        addSlotToContainer(tileEntity.inputItemHandler,2,8,0);
        addSlotToContainer(tileEntity.outputItemHandler,0,5,2);
        addSlotToContainer(tileEntity.outputItemHandler,1,8,2);
    }
}
