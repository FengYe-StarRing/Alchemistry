package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityGasTurbine;

public class BlockGasTurbine extends BlockMachine {
    public BlockGasTurbine(String name) {
        super(name,TileEntityGasTurbine.class,GuiHandler.GAS_TURBINE,0);
    }
}
