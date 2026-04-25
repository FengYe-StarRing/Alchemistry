package al132.alchemistry.compat.jei;

import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.RefineryChamberRecipe;
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

public class RefineryChamberRecipeCategory extends RecipeCategoryBase<RefineryChamberRecipe> {
    public RefineryChamberRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper,guiHelper.createBlankDrawable(144,18),ModBlocks.refineryChamber);
    }

    @Override
    public String getUid() {
        return AlchemistryJEIPlugin.REFINERY_CHAMBER;
    }

    @Override
    public String getTitle() {
        return I18n.format("tile.refinery_chamber.name");
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,RefineryChamberRecipe recipe,IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        List<FluidStack> inputs1 = ingredients.getInputs(VanillaTypes.FLUID).get(0);
        List<FluidStack> inputs2 = ingredients.getInputs(VanillaTypes.FLUID).get(1);
        List<FluidStack> outputs1 = ingredients.getOutputs(VanillaTypes.FLUID).get(0);
        List<FluidStack> outputs2 = ingredients.getOutputs(VanillaTypes.FLUID).get(1);
        List<FluidStack> outputs3 = ingredients.getOutputs(VanillaTypes.FLUID).get(2);
        List<FluidStack> outputs4 = ingredients.getOutputs(VanillaTypes.FLUID).get(3);
        List<ItemStack> itemInputs = ingredients.getOutputs(VanillaTypes.ITEM).get(0);

        fluidStackGroup.init(0,true,1,1,16,16,inputs1.get(0).amount,false,null);
        fluidStackGroup.set(0,inputs1);
        fluidStackGroup.init(1,true,19,1,16,16,inputs2.get(0).amount,false,null);
        fluidStackGroup.set(1,inputs2);
        if(recipe.petroleumGas != null) {
            fluidStackGroup.init(2,false,55,1,16,16,outputs1.get(0).amount,false,null);
            fluidStackGroup.set(2,outputs1);
        }
        if(recipe.lightFraction != null) {
            fluidStackGroup.init(3,false,73,1,16,16,outputs2.get(0).amount,false,null);
            fluidStackGroup.set(3,outputs2);
        }
        if(recipe.middleDistillate != null) {
            fluidStackGroup.init(4,false,91,1,16,16,outputs3.get(0).amount,false,null);
            fluidStackGroup.set(4,outputs3);
        }
        if(recipe.heavyFraction != null) {
            fluidStackGroup.init(5,false,109,1,16,16,outputs4.get(0).amount,false,null);
            fluidStackGroup.set(5,outputs4);
        }

        itemStackGroup.init(0,false,126,0);
        itemStackGroup.set(0,itemInputs);
    }

    @Override
    public void drawExtras(Minecraft mc) {
        itemSlot.draw(mc,0,0);
        itemSlot.draw(mc,18,0);
        leftArrow.draw(mc,18 * 2,0);
        itemSlot.draw(mc,18 * 3,0);
        itemSlot.draw(mc,18 * 4,0);
        itemSlot.draw(mc,18 * 5,0);
        itemSlot.draw(mc,18 * 6,0);
        itemSlot.draw(mc,18 * 7,0);
    }
}
