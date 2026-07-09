package al132.alchemistry.recipe;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.awt.*;

public class SolidFuelFiredBoilerRecipe implements IRecipeWrapper {
    public final int fuel;
    public final FluidStack input;
    public final FluidStack output;

    public SolidFuelFiredBoilerRecipe(int fuel,FluidStack input,FluidStack output) {
        this.fuel = fuel;
        this.input = input;
        this.output = output;
    }

    public static void init() {
        ModRecipes.addSolidFuelFiredBoilerRecipe(1,FluidRegistry.getFluidStack("water",50),FluidRegistry.getFluidStack("steam",50));
    }

    public boolean match(FluidStack input) {
        return this.input.isFluidEqual(input);
    }

    public boolean canApply(int fuel,FluidStack input,FluidTank outputTank) {
        return match(input) && fuel >= this.fuel && input.amount >= this.input.amount && (output.isFluidEqual(outputTank.getFluid()) || outputTank.getFluid() == null) && outputTank.getCapacity() - outputTank.getFluidAmount() >= output.amount;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID,input);
        ingredients.setOutput(VanillaTypes.FLUID,output);
    }

    @Override
    public void drawInfo(Minecraft mc,int recipeWidth,int recipeHeight,int mouseX,int mouseY) {
        String text = "-" + fuel + "fuel";
        mc.fontRenderer.drawString(text,106 - mc.fontRenderer.getStringWidth(text) / 2,9 - mc.fontRenderer.FONT_HEIGHT / 2,Color.black.getRGB());
    }
}
