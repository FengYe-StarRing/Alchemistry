package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerDistillationChamber;
import al132.alchemistry.tileentity.TileEntityDistillationChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiDistillationChamber extends GuiContainerBase<TileEntityDistillationChamber> {
    public GuiDistillationChamber(EntityPlayer player,TileEntity tile) {
        super(new ContainerDistillationChamber(player,tile),tile);
        setTitle("tile.distillation_chamber.name");
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks,mouseX,mouseY);
        // 绘制能量槽
        drawEnergyStorage(0,0,tileEntity.energyStorage);
        // 绘制流体槽
        drawFluidTank(18,0,tileEntity.fluidTanks[0]);
        drawFluidTank(18 * 7,0,tileEntity.fluidTanks[1]);
        // 绘制其他组件
        drawComponent(18 * 2,18,1,4);
        drawComponent(18 * 4,18,2,0);
        drawComponent(18 * 8,18,1,4);
    }

    @Override
    public void drawScreen(int mouseX,int mouseY,float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);
        // 绘制能量槽悬浮信息
        drawEnergyStorageHoveringText(0,0,tileEntity.energyStorage);
        // 绘制流体槽悬浮信息
        drawFluidTankHoveringText(18,0,tileEntity.fluidTanks[0]);
        drawFluidTankHoveringText(18 * 7,0,tileEntity.fluidTanks[1]);
    }
}
