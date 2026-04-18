package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityFluidFuelBoiler;

public class BlockFluidFuelBoiler extends BlockMachine {
    public BlockFluidFuelBoiler(String name) {
        super(name,TileEntityFluidFuelBoiler.class,GuiHandler.FLUID_FUEL_BOILER,0);
    }
}
