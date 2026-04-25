package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntitySolidFuelCell;

public class BlockSolidFuelCell extends BlockMachine {
    public BlockSolidFuelCell(String name) {
        super(name,TileEntitySolidFuelCell.class,GuiHandler.SOLID_FUEL_CELL,0);
    }
}
