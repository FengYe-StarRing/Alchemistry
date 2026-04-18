package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerFluidFuelBoiler;
import al132.alchemistry.tileentity.TileEntityFluidFuelBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiFluidFuelBoiler extends GuiContainerBase<TileEntityFluidFuelBoiler> {
    public GuiFluidFuelBoiler(EntityPlayer player,TileEntity tile) {
        super(new ContainerFluidFuelBoiler(player,tile),tile);
        setTitle("tile.fluid_fuel_boiler.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        // 绘制燃料槽
        drawComponent(0,0,0,0);
        drawComponent(0,0,0,3,(float)tileEntity.burnTime / (float)tileEntity.maxBurnTime);
        // 绘制流体槽
        drawFluidTank(18,0,tileEntity.fluidTanks[0]);
        drawFluidTank(18 * 6,0,tileEntity.fluidTanks[1]);
        drawFluidTank(18 * 7,0,tileEntity.fluidTanks[2]);
        // 绘制物品槽
        drawComponent(18 * 2,0,1,0);
        drawComponent(18 * 5,0,1,0);
        drawComponent(18 * 8,0,1,0);
        drawComponent(18 * 2,18 * 2,1,0);
        drawComponent(18 * 5,18 * 2,1,0);
        drawComponent(18 * 8,18 * 2,1,0);
        // 绘制其他组件
        drawComponent(18 * 2,18,1,4);
        drawComponent(18 * 5,18,1,4);
        drawComponent(18 * 8,18,1,4);
    }

    @Override
    public void drawScreen(int mouseX,int mouseY,float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);
        // 绘制流体槽悬浮信息
        drawFluidTankHoveringText(18,0,tileEntity.fluidTanks[0]);
        drawFluidTankHoveringText(18 * 6,0,tileEntity.fluidTanks[1]);
        drawFluidTankHoveringText(18 * 7,0,tileEntity.fluidTanks[2]);
    }
}
