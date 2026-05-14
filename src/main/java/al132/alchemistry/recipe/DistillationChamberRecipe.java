package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.CompoundRegistry;
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

public class DistillationChamberRecipe implements IRecipeWrapper {

    public final int energy,tick;
    public final FluidStack fluidInput,fluidOutput;
    public final ItemStack itemInput,itemOutput;

    public DistillationChamberRecipe(int energy,int tick,FluidStack fluidInput,ItemStack itemInput,FluidStack fluidOutput,ItemStack itemOutput) {
        this.energy = energy;
        this.tick = tick;
        this.fluidInput = fluidInput;
        this.itemInput = itemInput;
        this.fluidOutput = fluidOutput;
        this.itemOutput = itemOutput;
    }

    public static void init() {
        ModRecipes.addDistillationChamberRecipe(
                10,100,
                FluidRegistry.getFluidStack("water",1000),ItemStack.EMPTY,
                FluidRegistry.getFluidStack("distilled_water",1000),ItemStack.EMPTY
        );
        ModRecipes.addDistillationChamberRecipe(
                10,100,
                FluidRegistry.getFluidStack("salt_water",1000),ItemStack.EMPTY,
                FluidRegistry.getFluidStack("distilled_water",1000),ModItems.INSTANCE.getCompoundDust().toStack(CompoundRegistry.get("sodium_chloride").getKey())
        );
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID,fluidInput);
        ingredients.setInput(VanillaTypes.ITEM,itemInput);
        ingredients.setOutput(VanillaTypes.FLUID,fluidOutput);
        ingredients.setOutput(VanillaTypes.ITEM,itemOutput);
    }

    public boolean match(FluidStack fluidInput,ItemStack itemInput) {
        return (this.fluidInput == null || this.fluidInput.isFluidEqual(fluidInput)) && (this.itemInput.isEmpty() || ItemStack.areItemsEqual(this.itemInput,itemInput));
    }

    public boolean canApply(EnergyStorage storage,FluidTank inputFluidTank,ItemStack inputItemStack,FluidTank outputFluidTank,ItemStack outputItemStack) {
        return match(inputFluidTank.getFluid(),inputItemStack) && storage.extractEnergy(energy,true) >= energy
                && (fluidInput == null || inputFluidTank.getFluidAmount() >= fluidInput.amount)
                && (itemInput.isEmpty() || inputItemStack.getCount() >= itemInput.getCount())
                && (fluidOutput == null || outputFluidTank.fill(fluidOutput,false) >= fluidOutput.amount)
                && (itemOutput.isEmpty() || ItemStackUtil.canMerge(itemOutput,outputItemStack));
    }
}
