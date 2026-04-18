package al132.alchemistry.recipe;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidFuelBoilerRecipe {
    public final FluidStack input;
    public final int fuel;

    public FluidFuelBoilerRecipe(FluidStack input,int fuel) {
        this.input = input;
        this.fuel = fuel;
    }

    public static void init() {
        ModRecipes.addFluidFuelBoilerRecipe(FluidRegistry.getFluidStack("oil",1000),1600);
        ModRecipes.addFluidFuelBoilerRecipe(FluidRegistry.getFluidStack("natural_gas",1000),1600);
    }

    public boolean match(FluidStack input) {
        return this.input.isFluidEqual(input);
    }

    public boolean canApply(FluidStack input) {
        return match(input) && input.amount >= this.input.amount;
    }
}
