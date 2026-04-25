package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerSolidFuelFiredBoiler;
import al132.alchemistry.tileentity.TileEntitySolidFuelFiredBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiSolidFuelFiredBoiler extends GuiContainerBase<TileEntitySolidFuelFiredBoiler> {
    public GuiSolidFuelFiredBoiler(EntityPlayer player,TileEntity tile) {
        super(new ContainerSolidFuelFiredBoiler(player,tile),tile);
        setTitle("tile.solid_fuel_fired_boiler.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        // 绘制燃料槽
        drawComponent(0,0,0,0);
        drawComponent(0,0,0,3,(float)tileEntity.burnTime / (float)tileEntity.maxBurnTime);
        // 绘制流体槽
        drawFluidTank(18 * 6,0,tileEntity.fluidTanks[0]);
        drawFluidTank(18 * 7,0,tileEntity.fluidTanks[1]);
        // 绘制燃烧条
        drawComponent(18 * 2,18,1,5);
        if(tileEntity.burnTime != 0) {
            drawComponent(18 * 2,18,1,6);
        }
        // 绘制其他组件
        drawComponent(18,18 * 2,1,1);
        drawComponent(18 * 5,18,1,4);
        drawComponent(18 * 8,18,1,4);
    }

    @Override
    public void drawScreen(int mouseX,int mouseY,float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);
        // 绘制流体槽悬浮信息
        drawFluidTankHoveringText(18 * 6,0,tileEntity.fluidTanks[0]);
        drawFluidTankHoveringText(18 * 7,0,tileEntity.fluidTanks[1]);
    }
}
