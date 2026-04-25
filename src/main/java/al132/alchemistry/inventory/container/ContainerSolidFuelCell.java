package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntitySolidFuelCell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerSolidFuelCell extends ContainerMachine<TileEntitySolidFuelCell> {
    public ContainerSolidFuelCell(EntityPlayer player,TileEntity tile) {
        super(player,tile);
    }
}
