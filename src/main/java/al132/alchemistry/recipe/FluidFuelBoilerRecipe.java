package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;
import java.util.Map;

public class FluidFuelBoilerRecipe implements IRecipeWrapper {
    public final FluidStack input;
    public final int fuel;

    public FluidFuelBoilerRecipe(FluidStack input,int fuel) {
        this.input = input;
        this.fuel = fuel;
    }

    public static void init() {
        for(Map.Entry<Integer,ChemicalElement> entry : ElementRegistry.get(new String[]{"fluid"})) {
            ChemicalElement element = entry.getValue();
            int burnTime = element.burnTime / 80;
            if(burnTime > 0) {
                ModRecipes.addFluidFuelBoilerRecipe(FluidRegistry.getFluidStack(element.name,1000),burnTime);
            }
        }
        for(Map.Entry<Integer,ChemicalCompound> entry : CompoundRegistry.get(new String[]{"fluid"})) {
            ChemicalCompound compound = entry.getValue();
            int burnTime = compound.getBurnTime() / 80;
            if(burnTime > 0) {
                ModRecipes.addFluidFuelBoilerRecipe(FluidRegistry.getFluidStack(compound.name,1000),burnTime);
            }
        }
        for(Map.Entry<Integer,ChemicalMixture> entry : MixtureRegistry.get(new String[]{"fluid"})) {
            ChemicalMixture mixture = entry.getValue();
            int burnTime = mixture.getBurnTime() / 80;
            if(burnTime > 0) {
                ModRecipes.addFluidFuelBoilerRecipe(FluidRegistry.getFluidStack(mixture.name,1000),burnTime);
            }
        }
    }

    public boolean match(FluidStack input) {
        return this.input.isFluidEqual(input);
    }

    public boolean canApply(FluidStack input) {
        return match(input) && input.amount >= this.input.amount;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID,input);
    }

    @Override
    public void drawInfo(Minecraft mc,int recipeWidth,int recipeHeight,int mouseX,int mouseY) {
        String text = "+" + fuel + "fuel";
        mc.fontRenderer.drawString(text,63 - mc.fontRenderer.getStringWidth(text) / 2,9 - mc.fontRenderer.FONT_HEIGHT / 2,Color.black.getRGB());
    }
}
