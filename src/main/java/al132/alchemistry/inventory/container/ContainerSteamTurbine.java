package al132.alchemistry.inventory.container;

import al132.alchemistry.tileentity.TileEntitySteamTurbine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerSteamTurbine extends ContainerMachine<TileEntitySteamTurbine> {
    public ContainerSteamTurbine(EntityPlayer player,TileEntity tile) {
        super(player,tile);
    }
}
