package al132.alchemistry.recipe;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class SolidFuelFiredBoilerRecipe {
    public final int fuel;
    public final FluidStack input;
    public final FluidStack output;

    public SolidFuelFiredBoilerRecipe(int fuel,FluidStack input,FluidStack output) {
        this.fuel = fuel;
        this.input = input;
        this.output = output;
    }

    public static void init() {
        ModRecipes.addSolidFuelFiredBoilerRecipe(16,FluidRegistry.getFluidStack("water",10),FluidRegistry.getFluidStack("steam",10));
    }

    public boolean match(FluidStack input) {
        return this.input.isFluidEqual(input);
    }

    public boolean canApply(int fuel,FluidStack input,FluidTank outputTank) {
        return match(input) && fuel >= this.fuel && input.amount >= this.input.amount && (output.isFluidEqual(outputTank.getFluid()) || outputTank.getFluid() == null) && outputTank.getCapacity() - outputTank.getFluidAmount() >= output.amount;
    }
}
