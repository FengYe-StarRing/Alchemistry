package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerSteamTurbine;
import al132.alchemistry.tileentity.TileEntitySteamTurbine;
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
        drawInfoBackground("最大蒸汽流量: " + tileEntity.fluidTanks[0].getCapacity(),"蒸汽流量: " + tileEntity.steamFlow,"内部电量缓存: " + tileEntity.energyStorage.getEnergyStored());
    }
}
