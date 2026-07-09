package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerReservoir;
import al132.alchemistry.tileentity.TileEntityReservoir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiReservoir extends GuiContainerBase<TileEntityReservoir> {
    public GuiReservoir(EntityPlayer player,TileEntity tile) {
        super(new ContainerReservoir(player,tile),tile);
        setTitle("tile.reservoir.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        drawInfoBackground();
    }
}
