package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerRefineryChamber;
import al132.alchemistry.tileentity.TileEntityRefineryChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiRefineryChamber extends GuiContainerBase<TileEntityRefineryChamber> {
    public GuiRefineryChamber(EntityPlayer player,TileEntity tile) {
        super(new ContainerRefineryChamber(player,tile),tile);
        setTitle("tile.refinery_chamber.name");
        setUIType("large");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        // 绘制流体槽
        drawFluidTank(18,0,tileEntity.fluidTanks[0]);
        drawFluidTank(18 * 2,0,tileEntity.fluidTanks[1]);
        drawFluidTank(18 * 4,0,tileEntity.fluidTanks[2]);
        drawFluidTank(18 * 5,0,tileEntity.fluidTanks[3]);
        drawFluidTank(18 * 6,0,tileEntity.fluidTanks[4]);
        drawFluidTank(18 * 7,0,tileEntity.fluidTanks[5]);
        // 绘制能量槽
        drawEnergyStorage(0,0,tileEntity.energyStorage);
        // 绘制其他组件
        drawComponent(18,18 * 4,1,4);
        drawComponent(18 * 2,18 * 4,1,4);
        drawComponent(18 * 3,18,1,3);
        drawComponent(18 * 4,18 * 4,1,4);
        drawComponent(18 * 5,18 * 4,1,4);
        drawComponent(18 * 6,18 * 4,1,4);
        drawComponent(18 * 7,18 * 4,1,4);
    }

    @Override
    public void drawScreen(int mouseX,int mouseY,float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);
        // 绘制流体槽悬浮信息
        drawFluidTankHoveringText(18,0,tileEntity.fluidTanks[0]);
        drawFluidTankHoveringText(18 * 2,0,tileEntity.fluidTanks[1]);
        drawFluidTankHoveringText(18 * 4,0,tileEntity.fluidTanks[2]);
        drawFluidTankHoveringText(18 * 5,0,tileEntity.fluidTanks[3]);
        drawFluidTankHoveringText(18 * 6,0,tileEntity.fluidTanks[4]);
        drawFluidTankHoveringText(18 * 7,0,tileEntity.fluidTanks[5]);
        // 绘制能量槽悬浮信息
        drawEnergyStorageHoveringText(0,0,tileEntity.energyStorage);
    }
}
