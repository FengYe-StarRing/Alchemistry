package al132.alchemistry.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerLargeGuiMachine<T extends TileEntity> extends ContainerBase {
    public final T tileEntity;

    public ContainerLargeGuiMachine(EntityPlayer player,TileEntity tile) {
        int offsetY = 54;
        for(int i = 0;i < 9;i++) {
            addSlotToContainer(new Slot(player.inventory,i,8 + i * 18,142 + offsetY));
        }
        for(int i = 0;i < 3;i++) {
            for (int j = 0;j < 9;j++) {
                addSlotToContainer(new Slot(player.inventory,(i + 1) * 9 + j,8 + j * 18,84 + offsetY + i * 18));
            }
        }
        tileEntity = (T)tile;
    }
}
