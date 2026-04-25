package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerSteamTurbine;
import al132.alchemistry.tileentity.TileEntitySteamTurbine;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiSteamTurbine extends GuiContainerBase<TileEntitySteamTurbine> {
    public GuiSteamTurbine(EntityPlayer player,TileEntity tile) {
        super(new ContainerSteamTurbine(player,tile),tile);
        setTitle("tile.steam_turbine.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        drawInfoBackground(
                tileEntity.steamFlow == 0 ? I18n.format("gui.text0") : I18n.format("gui.text1"),
                I18n.format("gui.steam_turbine.text0",tileEntity.fluidTanks[0].getCapacity()),
                I18n.format("gui.steam_turbine.text1",tileEntity.steamFlow),
                I18n.format("gui.text3",tileEntity.outputPower)
        );
    }
}
