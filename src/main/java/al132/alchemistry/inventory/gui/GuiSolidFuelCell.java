package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerSolidFuelCell;
import al132.alchemistry.tileentity.TileEntitySolidFuelCell;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GuiSolidFuelCell extends GuiContainerBase<TileEntitySolidFuelCell> {
    public GuiSolidFuelCell(EntityPlayer player,TileEntity tile) {
        super(new ContainerSolidFuelCell(player,tile),tile);
        setTitle("tile.solid_fuel_cell.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        float progress = 0;
        if(tileEntity.energy != 0 && tileEntity.tick != 0) {
            progress = BigDecimal.valueOf((float)tileEntity.timer.tick / (float)tileEntity.tick * 100).setScale(1,RoundingMode.HALF_UP).floatValue();
        }
        drawInfoBackground(
                progress == 0 ? I18n.format("gui.text0") : I18n.format("gui.text2",progress),
                I18n.format("gui.text3",tileEntity.outputPower)
        );
    }
}
