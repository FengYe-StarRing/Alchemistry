package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerGasTurbine;
import al132.alchemistry.tileentity.TileEntityGasTurbine;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiGasTurbine extends GuiContainerBase<TileEntityGasTurbine> {
    public GuiGasTurbine(EntityPlayer player,TileEntity tile) {
        super(new ContainerGasTurbine(player,tile),tile);
        setTitle("tile.gas_turbine.name");
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
