package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntitySolidFuelFiredBoiler;

public class BlockSolidFuelFiredBoiler extends BlockMachine {
    public BlockSolidFuelFiredBoiler(String name) {
        super(name,TileEntitySolidFuelFiredBoiler.class,GuiHandler.SOLID_FUEL_FIRED_BOILER,0);
    }
}
