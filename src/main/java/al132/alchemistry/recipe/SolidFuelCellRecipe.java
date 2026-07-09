package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.*;
import al132.alchemistry.items.ModItems;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;
import java.util.Map;

public class SolidFuelCellRecipe implements IRecipeWrapper {
    public final ItemStack fuel;
    public final FluidStack oxygen;
    public final int energy;
    public final int tick;

    public SolidFuelCellRecipe(ItemStack fuel,FluidStack oxygen,int energy,int tick) {
        this.fuel = fuel;
        this.oxygen = oxygen;
        this.energy = energy;
        this.tick = tick;
    }

    public static void init() {
        FluidStack oxygen = FluidRegistry.getFluidStack("oxygen",1000);
        for(Map.Entry<Integer,ChemicalElement> entry : ElementRegistry.get(new String[]{"dust"})) {
            ChemicalElement element = entry.getValue();
            if(element.burnTime > 0) {
                ModRecipes.addSolidFuelCellRecipe(ModItems.INSTANCE.getElementDust().toStack(entry.getKey()),oxygen,element.burnTime / ElementRegistry.CARBON_BURN_TIME * 2,1000);
            }
        }
        for(Map.Entry<Integer,ChemicalCompound> entry : CompoundRegistry.get(new String[]{"default"})) {
            ChemicalCompound compound = entry.getValue();
            int burnTime = compound.getBurnTime();
            if(burnTime > 0) {
                ModRecipes.addSolidFuelCellRecipe(ModItems.INSTANCE.getCompounds().toStack(entry.getKey()),oxygen,burnTime / ElementRegistry.CARBON_BURN_TIME * 2,1000);
            }
        }
        for(Map.Entry<Integer,ChemicalMixture> entry : MixtureRegistry.get(new String[]{"default"})) {
            ChemicalMixture mixture = entry.getValue();
            int burnTime = mixture.getBurnTime();
            if(burnTime > 0) {
                ModRecipes.addSolidFuelCellRecipe(ModItems.INSTANCE.getMixture().toStack(entry.getKey()),oxygen,burnTime / ElementRegistry.CARBON_BURN_TIME * 2,1000);
            }
        }
    }

    public boolean match(ItemStack fuel,FluidStack oxygen) {
        return ItemStack.areItemsEqual(this.fuel,fuel) && this.oxygen.isFluidEqual(oxygen);
    }

    public boolean canApply(ItemStack fuel,FluidStack oxygen) {
        return match(fuel,oxygen) && oxygen.amount >= this.oxygen.amount;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM,fuel);
        ingredients.setInput(VanillaTypes.FLUID,oxygen);
    }

    @Override
    public void drawInfo(Minecraft mc,int recipeWidth,int recipeHeight,int mouseX,int mouseY) {
        String text = "+" + energy * tick + "FE";
        mc.fontRenderer.drawString(text,72 - mc.fontRenderer.getStringWidth(text) / 2,9 - mc.fontRenderer.FONT_HEIGHT / 2,Color.black.getRGB());
    }
}
