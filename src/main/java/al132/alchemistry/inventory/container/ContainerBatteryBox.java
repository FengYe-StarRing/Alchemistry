package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityBatteryBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerBatteryBox extends ContainerMachine<TileEntityBatteryBox> {
    public ContainerBatteryBox(EntityPlayer player,TileEntity tile) {
        super(player,tile);
        int index = 0;
        for(int row = 0;row < 3;row++) {
            for(int column = 0;column < 9;column++) {
                addSlotToContainer(tileEntity.inputItemHandler,index++,row,column);
            }
        }
    }
}
