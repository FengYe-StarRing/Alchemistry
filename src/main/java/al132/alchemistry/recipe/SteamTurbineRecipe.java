package al132.alchemistry.recipe;

import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class SteamTurbineRecipe {
    public final FluidStack input;
    public final int output;

    public SteamTurbineRecipe(FluidStack input,int output) {
        this.input = input;
        this.output = output;
    }

    public static void init() {
        ModRecipes.addSteamTurbineRecipe(FluidRegistry.getFluidStack("steam",1),1);
    }

    public boolean match(FluidStack input) {
        return this.input.isFluidEqual(input);
    }

    public boolean canApply(FluidStack input,EnergyStorage storage) {
        return match(input) && input.amount >= this.input.amount && storage.receiveEnergy(output,true) >= output;
    }
}
