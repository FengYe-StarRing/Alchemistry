package al132.alchemistry.compat.jei;

import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.DistillationChamberRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class DistillationChamberRecipeCategory extends RecipeCategoryBase<DistillationChamberRecipe> {
    public DistillationChamberRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper,guiHelper.createBlankDrawable(90,18),ModBlocks.distillationChamber);
    }

    @Override
    public String getUid() {
        return AlchemistryJEIPlugin.DISTILLATION_CHAMBER;
    }

    @Override
    public String getTitle() {
        return I18n.format("tile.distillation_chamber.name");
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,DistillationChamberRecipe recipeWrapper,IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        List<FluidStack> fluidInput = ingredients.getInputs(VanillaTypes.FLUID).get(0);
        List<ItemStack> itemInput = ingredients.getInputs(VanillaTypes.ITEM).get(0);
        List<FluidStack> fluidOutput = ingredients.getOutputs(VanillaTypes.FLUID).get(0);
        List<ItemStack> itemOutput = ingredients.getOutputs(VanillaTypes.ITEM).get(0);

        if(recipeWrapper.fluidInput != null) {
            fluidStackGroup.init(0,true,1,1,16,16,fluidInput.get(0).amount,false,null);
            fluidStackGroup.set(0,fluidInput);
        }
        if(!recipeWrapper.itemInput.isEmpty()) {
            itemStackGroup.init(0,true,18,0);
            itemStackGroup.set(0,itemInput);
        }
        if(recipeWrapper.fluidOutput != null) {
            fluidStackGroup.init(1,false,55,1,16,16,fluidOutput.get(0).amount,false,null);
            fluidStackGroup.set(1,fluidOutput);
        }
        if(!recipeWrapper.itemOutput.isEmpty()) {
            itemStackGroup.init(1,false,72,0);
            itemStackGroup.set(1,itemOutput);
        }
    }

    @Override
    public void drawExtras(Minecraft mc) {
        itemSlot.draw(mc,0,0);
        itemSlot.draw(mc,18,0);
        leftArrow.draw(mc,36,0);
        itemSlot.draw(mc,54,0);
        itemSlot.draw(mc,72,0);
    }
}
