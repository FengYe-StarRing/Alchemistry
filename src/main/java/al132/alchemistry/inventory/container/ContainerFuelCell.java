package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityFuelCell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerFuelCell extends ContainerMachine<TileEntityFuelCell> {
    public ContainerFuelCell(EntityPlayer player,TileEntity tile) {
        super(player,tile);
    }
}
