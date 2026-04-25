package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.ElementRegistry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;
import java.util.Arrays;

public class FuelCellRecipe implements IRecipeWrapper {
    public final FluidStack input1;
    public final FluidStack input2;
    public final int energy;

    public FuelCellRecipe(FluidStack input1,FluidStack input2,int energy) {
        this.input1 = input1;
        this.input2 = input2;
        this.energy = energy;
    }

    public static void init() {
        for(FluidFuelBoilerRecipe recipe : ModRecipes.fluidFuelBoilerRecipes) {
            FluidStack stack = recipe.input.copy();
            stack.amount /= 1000;
            ModRecipes.addFuelCellRecipe(stack,FluidRegistry.getFluidStack("oxygen",stack.amount),recipe.fuel / ElementRegistry.CARBON_FURN_TIME * 2);
        }
    }

    public boolean match(FluidStack input1,FluidStack input2) {
        return this.input1.isFluidEqual(input1) && this.input2.isFluidEqual(input2);
    }

    public boolean canApply(FluidStack input1,FluidStack input2,EnergyStorage storage) {
        return match(input1,input2) && storage.receiveEnergy(energy,true) >= energy && input1.amount >= this.input1.amount && input2.amount >= this.input2.amount;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.FLUID,Arrays.asList(input1,input2));
    }

    @Override
    public void drawInfo(Minecraft mc,int recipeWidth,int recipeHeight,int mouseX,int mouseY) {
        String text = "+" + energy + "FE";
        mc.fontRenderer.drawString(text,72 - mc.fontRenderer.getStringWidth(text) / 2,9 - mc.fontRenderer.FONT_HEIGHT / 2,Color.black.getRGB());
    }
}
