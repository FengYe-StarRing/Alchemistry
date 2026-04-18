package al132.alchemistry.inventory.gui;

import al132.alchemistry.Reference;
import al132.alchemistry.util.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public abstract class GuiContainerBase<T extends TileEntity> extends GuiContainer {
    public final T tileEntity;
    public String title = "";

    public GuiContainerBase(Container inventorySlotsIn,TileEntity tile) {
        super(inventorySlotsIn);
        tileEntity = (T)tile;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID,"textures/gui/gui.png"));
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
    }

    @Override
    public void drawScreen(int mouseX,int mouseY,float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX,mouseY,partialTicks);
        renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX,int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX,mouseY);
        String title = I18n.format(this.title);
        fontRenderer.drawString(title,xSize / 2 - fontRenderer.getStringWidth(title) / 2,6,4210752);
        fontRenderer.drawString(I18n.format("container.inventory"),8,ySize - 96 + 2,4210752);
    }

    public void drawComponent(int offsetX, int offsetY, int line, int row, float bar) {
        offsetX += 7;
        offsetY += 16;
        int textureX,textureY,width,height;
        switch(line) {
            default:
            case 0:
                textureX = row * 18;
                textureY = 0;
                width = 18;
                height = 54;
                break;
            case 1:
                textureX = row * 18;
                textureY = 54;
                width = 18;
                height = 18;
                break;
        }
        bar = (int)(height - height * bar);
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID,"textures/gui/component.png"));
        drawTexturedModalRect(guiLeft + offsetX,guiTop + offsetY + bar,textureX,textureY + (int)bar,width,height - (int)bar);
    }

    public void drawComponent(int offsetX,int offsetY,int line,int row) {
        drawComponent(offsetX,offsetY,line,row,1F);
    }

    public void drawFluidTank(int offsetX,int offsetY,FluidTank tank) {
        int height = 54;
        int bar = (int)(height * (float)tank.getFluidAmount() / (float)tank.getCapacity());
        drawComponent(offsetX,offsetY,0,2);
        offsetX += 7;
        offsetY += 16;
        RenderUtil.drawFluid(tank,guiLeft + offsetX + 1,guiTop + offsetY + 1 + height - bar,18 - 2,bar - 2);
    }

    public void drawFluidTankHoveringText(int offsetX,int offsetY,FluidTank tank) {
        offsetX += 7;
        offsetY += 16;
        FluidStack fluidStack = tank.getFluid();
        List<String> texts = new ArrayList<>();
        texts.add(fluidStack == null ? "None" : fluidStack.getLocalizedName());
        texts.add(tank.getFluidAmount() + "mb/" + tank.getCapacity() + "mb");
        RenderUtil.drawHoveringText(this,texts,guiLeft + offsetX,guiTop + offsetY,18,54);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void drawInfoBackground(String... texts) {
        RenderUtil.drawRect(guiLeft + 7,guiTop + 16,18 * 9,18 * 3,Color.gray.getRGB());
        for(int i = 0;i < 6 && i < texts.length;i++) {
            fontRenderer.drawString(texts[i],guiLeft + 8,guiTop + 16 + fontRenderer.FONT_HEIGHT * i,Color.white.getRGB());
        }
    }
}
