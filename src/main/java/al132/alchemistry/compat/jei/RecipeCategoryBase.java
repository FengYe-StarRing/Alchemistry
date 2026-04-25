package al132.alchemistry.compat.jei;

import al132.alchemistry.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public abstract class RecipeCategoryBase<T extends IRecipeWrapper> implements IRecipeCategory<T> {
    public static final ResourceLocation component = new ResourceLocation(Reference.MODID,"textures/gui/component.png");
    public final IGuiHelper guiHelper;
    public final IDrawable background;
    public final IDrawable icon;
    public final IDrawable itemSlot;
    public final IDrawable burnOFF;
    public final IDrawable burnON;
    public final IDrawable leftArrow;

    public RecipeCategoryBase(IGuiHelper guiHelper,IDrawable background,Block block) {
        this.guiHelper = guiHelper;
        this.background = background;
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(block));
        itemSlot = guiHelper.createDrawable(component,0,54,18,18);
        burnOFF = guiHelper.createDrawable(component,90,54,18,18);
        burnON = guiHelper.createDrawable(component,108,54,18,18);
        leftArrow = guiHelper.createDrawable(component,54,54,18,18);
    }

    @Override
    public String getModName() {
        return Reference.MODNAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return icon;
    }
}
