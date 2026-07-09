package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityReservoir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerReservoir extends ContainerMachine<TileEntityReservoir> {
    public ContainerReservoir(EntityPlayer player,TileEntity tile) {
        super(player,tile);
    }
}
