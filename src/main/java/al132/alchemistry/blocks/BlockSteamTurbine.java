package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntitySteamTurbine;

public class BlockSteamTurbine extends BlockMachine {
    public BlockSteamTurbine(String name) {
        super(name,TileEntitySteamTurbine.class,GuiHandler.STEAM_TURBINE,0);
    }
}
