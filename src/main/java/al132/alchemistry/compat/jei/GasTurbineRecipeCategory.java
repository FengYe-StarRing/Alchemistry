package al132.alchemistry.compat.jei;

import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.GasTurbineRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class GasTurbineRecipeCategory extends RecipeCategoryBase<GasTurbineRecipe> {
    public GasTurbineRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper,guiHelper.createBlankDrawable(72,18),ModBlocks.gasTurbine);
    }

    @Override
    public String getUid() {
        return AlchemistryJEIPlugin.GAS_TURBINE;
    }

    @Override
    public String getTitle() {
        return I18n.format("tile.gas_turbine.name");
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,GasTurbineRecipe recipeWrapper,IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        List<FluidStack> inputs = ingredients.getInputs(VanillaTypes.FLUID).get(0);
        FluidStack input = inputs.get(0);

        fluidStackGroup.init(0,true,1,1,16,16,input.amount,false,null);
        fluidStackGroup.set(0,inputs);
    }

    @Override
    public void drawExtras(Minecraft mc) {
        itemSlot.draw(mc,0,0);
        leftArrow.draw(mc,18,0);
    }
}
