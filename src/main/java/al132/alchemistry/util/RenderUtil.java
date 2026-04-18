package al132.alchemistry.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class RenderUtil {
    public static final Minecraft mc = Minecraft.getMinecraft();

    public static void drawFluid(FluidStack stuck,int x,int y,int width,int height) {
        if (stuck == null || stuck.getFluid() == null) {
            return;
        }

        Fluid fluid = stuck.getFluid();
        int fluidColor = fluid.getColor(stuck);

        // 解析颜色值
        float red = (fluidColor >> 16 & 255) / 255.0F;
        float green = (fluidColor >> 8 & 255) / 255.0F;
        float blue = (fluidColor & 255) / 255.0F;
        float alpha = (fluidColor >> 24 & 255) / 255.0F;

        // 获取流体纹理
        TextureMap textureMap = mc.getTextureMapBlocks();
        TextureAtlasSprite fluidTexture = textureMap.getTextureExtry(fluid.getStill().toString());

        // 如果静止纹理不存在，尝试使用流动纹理
        if (fluidTexture == null) {
            fluidTexture = textureMap.getTextureExtry(fluid.getFlowing().toString());
        }

        // 如果都不存在，使用缺失纹理
        if (fluidTexture == null) {
            fluidTexture = textureMap.getMissingSprite();
        }

        // 绑定纹理并设置颜色
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.color(red, green, blue, alpha);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // 准备绘制
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        // 计算UV坐标
        float uMin = fluidTexture.getMinU();
        float uMax = fluidTexture.getMaxU();
        float vMin = fluidTexture.getMinV();
        float vMax = fluidTexture.getMaxV();

        // 渲染流体矩形（处理纹理平铺）
        int textureWidth = fluidTexture.getIconWidth();
        int textureHeight = fluidTexture.getIconHeight();

        for (int i = 0; i < width; i += textureWidth) {
            for (int j = 0; j < height; j += textureHeight) {
                int drawWidth = Math.min(textureWidth, width - i);
                int drawHeight = Math.min(textureHeight, height - j);

                float u1 = uMin;
                float u2 = uMin + (uMax - uMin) * drawWidth / textureWidth;
                float v1 = vMin;
                float v2 = vMin + (vMax - vMin) * drawHeight / textureHeight;

                buffer.pos(x + i, y + j + drawHeight, 0).tex(u1, v2).endVertex();
                buffer.pos(x + i + drawWidth, y + j + drawHeight, 0).tex(u2, v2).endVertex();
                buffer.pos(x + i + drawWidth, y + j, 0).tex(u2, v1).endVertex();
                buffer.pos(x + i, y + j, 0).tex(u1, v1).endVertex();
            }
        }

        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawFluid(FluidTank tank,int x,int y,int width,int height) {
        drawFluid(tank.getFluid(),x,y,width,height);
    }

    public static ScaledResolution getScaledResolution() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }

    public static int getMouseX() {
        return Mouse.getX() * getScaledResolution().getScaledWidth() / Minecraft.getMinecraft().displayWidth;
    }

    public static int getMouseY() {
        int height = getScaledResolution().getScaledHeight();
        return height - Mouse.getY() * height / Minecraft.getMinecraft().displayHeight - 1;
    }

    public static boolean isMouseHovered(int x,int y,int width,int height) {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public static void drawHoveringText(GuiScreen gui,String text,int x,int y,int width,int height) {
        if(isMouseHovered(x,y,width,height)) {
            gui.drawHoveringText(text,getMouseX(),getMouseY());
        }
    }

    public static void drawHoveringText(GuiScreen gui,List<String> textLines,int x,int y,int width,int height) {
        if(isMouseHovered(x,y,width,height)) {
            gui.drawHoveringText(textLines,getMouseX(),getMouseY());
        }
    }

    public static void drawRect(int x,int y,int width,int height,int color) {
        Gui.drawRect(x,y,x + width,y + height,color);
    }
}
