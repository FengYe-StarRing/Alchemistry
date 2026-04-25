package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityRefineryChamber;

public class BlockRefineryChamber extends BlockMachine {
    public BlockRefineryChamber(String name) {
        super(name,TileEntityRefineryChamber.class,GuiHandler.REFINERY_CHAMBER,0);
    }
}
