package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerBatteryBox;
import al132.alchemistry.tileentity.TileEntityBatteryBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiBatteryBox extends GuiContainerBase<TileEntityBatteryBox> {
    public GuiBatteryBox(EntityPlayer player,TileEntity tile) {
        super(new ContainerBatteryBox(player,tile),tile);
        setTitle("tile.battery_box.name");
    }
}
