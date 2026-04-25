package al132.alchemistry.inventory.gui;

import al132.alchemistry.Reference;
import al132.alchemistry.util.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.SlotItemHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GuiContainerBase<T extends TileEntity> extends GuiContainer {
    public final T tileEntity;
    public final Container container;
    public String title = "";
    public String uiType = "standard";

    public GuiContainerBase(Container container,TileEntity tile) {
        super(container);
        tileEntity = (T)tile;
        this.container = container;
    }

    @Override
    public void initGui() {
        if(uiType.equals("large")) ySize = 220;
        super.initGui();
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks,int mouseX,int mouseY) {
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID,"textures/gui/" + uiType + "_gui.png"));
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
        for(Slot slot : container.inventorySlots) {
            if(slot instanceof SlotItemHandler) {
                drawComponent(slot.xPos - 8,slot.yPos - 17,1,0);
            }
        }
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

    public void drawComponent(int offsetX,int offsetY,int row,int column,float bar) {
        offsetX += 7;
        offsetY += 16;
        int textureX,textureY,width,height;
        switch(row) {
            default:
            case 0:
                textureX = column * 18;
                textureY = 0;
                width = 18;
                height = 54;
                break;
            case 1:
                textureX = column * 18;
                textureY = 54;
                width = 18;
                height = 18;
                break;
        }
        bar = (int)(height - height * bar);
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID,"textures/gui/component.png"));
        drawTexturedModalRect(guiLeft + offsetX,guiTop + offsetY + bar,textureX,textureY + (int)bar,width,height - (int)bar);
    }

    public void drawComponent(int offsetX,int offsetY,int row,int column) {
        drawComponent(offsetX,offsetY,row,column,1F);
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
        int line = 3;
        if(uiType.equals("large")) line = 6;
        RenderUtil.drawRect(guiLeft + 7,guiTop + 16,18 * 9,18 * line,Color.gray.getRGB());
        for(int i = 0;i < 6 && i < texts.length;i++) {
            fontRenderer.drawString(texts[i],guiLeft + 8,guiTop + 16 + fontRenderer.FONT_HEIGHT * i,Color.white.getRGB());
        }
    }

    public void setUIType(String uiType) {
        this.uiType = uiType;
    }

    public void drawEnergyStorage(int offsetX,int offsetY,EnergyStorage storage) {
        drawComponent(offsetX,offsetY,0,0);
        drawComponent(offsetX,offsetY,0,1,(float)storage.getEnergyStored() / (float)storage.getMaxEnergyStored());
    }

    public void drawEnergyStorageHoveringText(int offsetX,int offsetY,EnergyStorage storage) {
        offsetX += 7;
        offsetY += 16;
        List<String> texts = new ArrayList<>();
        texts.add(storage.getEnergyStored() + "FE/" + storage.getMaxEnergyStored() + "FE");
        RenderUtil.drawHoveringText(this,texts,guiLeft + offsetX,guiTop + offsetY,18,54);
    }
}
