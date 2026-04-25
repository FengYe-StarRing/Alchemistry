package al132.alchemistry.compat.jei;

import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.SolidFuelCellRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class SolidFuelCellRecipeCategory extends RecipeCategoryBase<SolidFuelCellRecipe> {
    public SolidFuelCellRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper,guiHelper.createBlankDrawable(90,18),ModBlocks.solidFuelCell);
    }

    @Override
    public String getUid() {
        return AlchemistryJEIPlugin.SOLID_FUEL_CELL;
    }

    @Override
    public String getTitle() {
        return I18n.format("tile.solid_fuel_cell.name");
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,SolidFuelCellRecipe recipeWrapper,IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        List<ItemStack> fuel = ingredients.getInputs(VanillaTypes.ITEM).get(0);
        List<FluidStack> oxygen = ingredients.getInputs(VanillaTypes.FLUID).get(0);

        itemStackGroup.init(0,true,0,0);
        itemStackGroup.set(0,fuel);
        fluidStackGroup.init(0,true,19,1,16,16,oxygen.get(0).amount,false,null);
        fluidStackGroup.set(0,oxygen);
    }

    @Override
    public void drawExtras(Minecraft mc) {
        itemSlot.draw(mc,0,0);
        itemSlot.draw(mc,18,0);
        leftArrow.draw(mc,36,0);
    }
}
