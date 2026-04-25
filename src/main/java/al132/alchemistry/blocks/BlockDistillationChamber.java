package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityDistillationChamber;

public class BlockDistillationChamber extends BlockMachine {
    public BlockDistillationChamber(String name) {
        super(name,TileEntityDistillationChamber.class,GuiHandler.DISTILLATION_CHAMBER,0);
    }
}
