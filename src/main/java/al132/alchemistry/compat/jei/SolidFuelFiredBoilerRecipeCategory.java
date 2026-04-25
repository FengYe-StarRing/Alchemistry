package al132.alchemistry.compat.jei;

import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.SolidFuelFiredBoilerRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class SolidFuelFiredBoilerRecipeCategory extends RecipeCategoryBase<SolidFuelFiredBoilerRecipe> {
    public SolidFuelFiredBoilerRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper,guiHelper.createBlankDrawable(140,18),ModBlocks.solidFuelFiredBoiler);
    }

    @Override
    public String getUid() {
        return AlchemistryJEIPlugin.SOLID_FUEL_FIRED_BOILER;
    }

    @Override
    public String getTitle() {
        return I18n.format("tile.solid_fuel_fired_boiler.name");
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,SolidFuelFiredBoilerRecipe recipeWrapper,IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        List<FluidStack> inputs = ingredients.getInputs(VanillaTypes.FLUID).get(0);
        List<FluidStack> outputs = ingredients.getOutputs(VanillaTypes.FLUID).get(0);
        FluidStack input = inputs.get(0);
        FluidStack output = outputs.get(0);

        fluidStackGroup.init(0,true,19,1,16,16,input.amount,false,null);
        fluidStackGroup.set(0,inputs);

        fluidStackGroup.init(1,false,55,1,16,16,output.amount,false,null);
        fluidStackGroup.set(1,outputs);
    }

    @Override
    public void drawExtras(Minecraft mc) {
        burnOFF.draw(mc,0,0);
        burnON.draw(mc,0,0);
        itemSlot.draw(mc,18,0);
        leftArrow.draw(mc,36,0);
        itemSlot.draw(mc,54,0);
    }
}
