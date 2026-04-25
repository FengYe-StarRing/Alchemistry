package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityFuelCell;

public class BlockFuelCell extends BlockMachine {
    public BlockFuelCell(String name) {
        super(name,TileEntityFuelCell.class,GuiHandler.FUEL_CELL,0);
    }
}
