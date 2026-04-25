package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerFuelCell;
import al132.alchemistry.tileentity.TileEntityFuelCell;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiFuelCell extends GuiContainerBase<TileEntityFuelCell> {
    public GuiFuelCell(EntityPlayer player,TileEntity tile) {
        super(new ContainerFuelCell(player,tile),tile);
        setTitle("tile.fuel_cell.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        drawInfoBackground(
                tileEntity.outputPower == 0 ? I18n.format("gui.text0") : I18n.format("gui.text1"),
                I18n.format("gui.text3",tileEntity.outputPower)
        );
    }
}
