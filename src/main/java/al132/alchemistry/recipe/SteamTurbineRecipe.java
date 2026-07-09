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

public class SteamTurbineRecipe implements IRecipeWrapper {
    public final FluidStack input;
    public final FluidStack output;
    public final int energy;

    public SteamTurbineRecipe(FluidStack input,FluidStack output,int energy) {
        this.input = input;
        this.output = output;
        this.energy = energy;
    }

    public static void init() {
        ModRecipes.addSteamTurbineRecipe(FluidRegistry.getFluidStack("steam",1),FluidRegistry.getFluidStack("water",1),ElementRegistry.CARBON_BURN_ENERGY / 1000);
    }

    public boolean match(FluidStack input) {
        return this.input.isFluidEqual(input);
    }

    public boolean canApply(FluidStack input,EnergyStorage storage) {
        return match(input) && input.amount >= this.input.amount && storage.receiveEnergy(energy,true) >= energy;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID,input);
        ingredients.setOutput(VanillaTypes.FLUID,output);
    }

    @Override
    public void drawInfo(Minecraft mc,int recipeWidth,int recipeHeight,int mouseX,int mouseY) {
        String text = "+" + energy + "FE";
        mc.fontRenderer.drawString(text,81 - mc.fontRenderer.getStringWidth(text) / 2,9 - mc.fontRenderer.FONT_HEIGHT / 2,Color.black.getRGB());
    }
}
