package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityReservoir;

public class BlockReservoir extends BlockMachine {
    public BlockReservoir(String name) {
        super(name,TileEntityReservoir.class,GuiHandler.RESERVOIR,0);
    }
}
