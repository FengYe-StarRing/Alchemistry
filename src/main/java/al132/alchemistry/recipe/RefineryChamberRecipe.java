package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.ElementRegistry;
import al132.alchemistry.chemistry.MixtureRegistry;
import al132.alchemistry.items.ModItems;
import al132.alchemistry.util.ItemStackUtil;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.util.Arrays;

public class RefineryChamberRecipe implements IRecipeWrapper {
    public final int energy,tick;
    public final FluidStack input1;
    public final FluidStack input2;
    public final FluidStack petroleumGas;
    public final FluidStack lightFraction;
    public final FluidStack middleDistillate;
    public final FluidStack heavyFraction;
    public final ItemStack oilResidue;

    public RefineryChamberRecipe(int energy,int tick,FluidStack input1,FluidStack input2,FluidStack petroleumGas,FluidStack lightFraction,FluidStack middleDistillate,FluidStack heavyFraction,ItemStack oilResidue) {
        this.energy = energy;
        this.tick = tick;
        this.input1 = input1;
        this.input2 = input2;
        this.petroleumGas = petroleumGas;
        this.lightFraction = lightFraction;
        this.middleDistillate = middleDistillate;
        this.heavyFraction = heavyFraction;
        this.oilResidue = oilResidue;
    }

    public static void init() {
        ModRecipes.addRefineryChamberRecipe(10,100,
                FluidRegistry.getFluidStack("oil",1000),
                FluidRegistry.getFluidStack("distilled_water",1000),
                FluidRegistry.getFluidStack("refinery_gas",250),
                FluidRegistry.getFluidStack("naphtha",250),
                FluidRegistry.getFluidStack("light_fuel",250),
                FluidRegistry.getFluidStack("heavy_fuel",250),
                ModItems.INSTANCE.getMixture().toStack(MixtureRegistry.get("oil_residue").getKey())
        );
        ModRecipes.addRefineryChamberRecipe(10,100,
                FluidRegistry.getFluidStack("natural_gas",1000),
                FluidRegistry.getFluidStack("hydrogen",2000),
                FluidRegistry.getFluidStack("refinery_gas",1000),
                FluidRegistry.getFluidStack("hydrogen_sulfide",1000),
                null,null,ItemStack.EMPTY
        );
        ModRecipes.addRefineryChamberRecipe(10,100,
                FluidRegistry.getFluidStack("refinery_gas",1000),
                FluidRegistry.getFluidStack("hydrogen",1000),
                FluidRegistry.getFluidStack("methane",250),
                FluidRegistry.getFluidStack("ethane",250),
                FluidRegistry.getFluidStack("propane",250),
                FluidRegistry.getFluidStack("butane",250),
                ItemStack.EMPTY
        );
        ModRecipes.addRefineryChamberRecipe(10,100,
                FluidRegistry.getFluidStack("refinery_gas",1000),
                FluidRegistry.getFluidStack("steam",1000),
                FluidRegistry.getFluidStack("ethylene",250),
                FluidRegistry.getFluidStack("propylene",250),
                FluidRegistry.getFluidStack("butadiene",250),
                FluidRegistry.getFluidStack("butene",250),
                ModItems.INSTANCE.getElementDust().toStack(ElementRegistry.getAtomicNumber("carbon"),1)
        );
        ModRecipes.addRefineryChamberRecipe(10,100,
                FluidRegistry.getFluidStack("naphtha",1000),
                FluidRegistry.getFluidStack("hydrogen",1000),
                FluidRegistry.getFluidStack("pentane",250),
                FluidRegistry.getFluidStack("hexane",250),
                FluidRegistry.getFluidStack("heptane",250),
                FluidRegistry.getFluidStack("octane",250),
                ItemStack.EMPTY
        );
        ModRecipes.addRefineryChamberRecipe(10,100,
                FluidRegistry.getFluidStack("naphtha",1000),
                FluidRegistry.getFluidStack("steam",1000),
                FluidRegistry.getFluidStack("pentene",250),
                FluidRegistry.getFluidStack("hexene",250),
                FluidRegistry.getFluidStack("heptene",250),
                FluidRegistry.getFluidStack("octene",250),
                ModItems.INSTANCE.getElementDust().toStack(ElementRegistry.getAtomicNumber("carbon"),1)
        );
    }

    public boolean match(FluidStack input1,FluidStack input2) {
        return this.input1.isFluidEqual(input1) && this.input2.isFluidEqual(input2);
    }

    public boolean canApply(EnergyStorage storage,FluidTank input1,FluidTank input2,FluidTank petroleumGas,FluidTank lightFraction,FluidTank middleDistillate,FluidTank heavyFraction,ItemStack oilResidue) {
        return match(input1.getFluid(),input2.getFluid()) && storage.extractEnergy(energy,true) >= energy
                && this.input1.isFluidEqual(input1.getFluid()) && input1.getFluidAmount() >= this.input1.amount
                && this.input2.isFluidEqual(input2.getFluid()) && input2.getFluidAmount() >= this.input2.amount
                && (this.petroleumGas == null || (this.petroleumGas.isFluidEqual(petroleumGas.getFluid()) || petroleumGas.getFluid() == null) && petroleumGas.getCapacity() - petroleumGas.getFluidAmount() >= this.petroleumGas.amount)
                && (this.lightFraction == null || (this.lightFraction.isFluidEqual(lightFraction.getFluid()) || lightFraction.getFluid() == null) && lightFraction.getCapacity() - lightFraction.getFluidAmount() >= this.lightFraction.amount)
                && (this.middleDistillate == null || (this.middleDistillate.isFluidEqual(middleDistillate.getFluid()) || middleDistillate.getFluid() == null) && middleDistillate.getCapacity() - middleDistillate.getFluidAmount() >= this.middleDistillate.amount)
                && (this.heavyFraction == null || (this.heavyFraction.isFluidEqual(heavyFraction.getFluid()) || heavyFraction.getFluid() == null) && heavyFraction.getCapacity() - heavyFraction.getFluidAmount() >= this.heavyFraction.amount)
                && (this.oilResidue == null || ItemStackUtil.canMerge(this.oilResidue,oilResidue));
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.FLUID,Arrays.asList(input1,input2));
        ingredients.setOutputs(VanillaTypes.FLUID,Arrays.asList(petroleumGas,lightFraction,middleDistillate,heavyFraction));
        ingredients.setOutput(VanillaTypes.ITEM,oilResidue);
    }
}
