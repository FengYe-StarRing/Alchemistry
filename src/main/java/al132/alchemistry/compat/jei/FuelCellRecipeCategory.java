package al132.alchemistry.compat.jei;

import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.FuelCellRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FuelCellRecipeCategory extends RecipeCategoryBase<FuelCellRecipe> {
    public FuelCellRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper,guiHelper.createBlankDrawable(90,18),ModBlocks.fuelCell);
    }

    @Override
    public String getUid() {
        return AlchemistryJEIPlugin.FUEL_CELL;
    }

    @Override
    public String getTitle() {
        return I18n.format("tile.fuel_cell.name");
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,FuelCellRecipe recipeWrapper,IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        List<FluidStack> inputs1 = ingredients.getInputs(VanillaTypes.FLUID).get(0);
        List<FluidStack> inputs2 = ingredients.getInputs(VanillaTypes.FLUID).get(1);

        fluidStackGroup.init(0,true,1,1,16,16,inputs1.get(0).amount,false,null);
        fluidStackGroup.set(0,inputs1);
        fluidStackGroup.init(1,true,19,1,16,16,inputs2.get(0).amount,false,null);
        fluidStackGroup.set(1,inputs2);
    }

    @Override
    public void drawExtras(Minecraft mc) {
        itemSlot.draw(mc,0,0);
        itemSlot.draw(mc,18,0);
        leftArrow.draw(mc,36,0);
    }
}
