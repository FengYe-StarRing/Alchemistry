package al132.alchemistry.blocks;

import al132.alchemistry.inventory.gui.GuiHandler;
import al132.alchemistry.tileentity.TileEntityBatteryBox;

public class BlockBatteryBox extends BlockMachine {
    public BlockBatteryBox(String name) {
        super(name,TileEntityBatteryBox.class,GuiHandler.BATTERY_BOX,0);
    }
}
