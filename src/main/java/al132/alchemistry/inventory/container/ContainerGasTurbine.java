package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntityGasTurbine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerGasTurbine extends ContainerMachine<TileEntityGasTurbine> {
    public ContainerGasTurbine(EntityPlayer player,TileEntity tile) {
        super(player,tile);
    }
}
