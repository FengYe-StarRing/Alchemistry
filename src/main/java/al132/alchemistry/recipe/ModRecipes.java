package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.ChemicalElement;
import al132.alchemistry.chemistry.ElementRegistry;
import al132.alchemistry.items.ModItems;
import kotlin.text.StringsKt;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModRecipes {
    public static final List<SolidFuelFiredBoilerRecipe> solidFuelFiredBoilerRecipes = new ArrayList<>();
    public static final List<SteamTurbineRecipe> steamTurbineRecipes = new ArrayList<>();
    public static final List<FluidFuelBoilerRecipe> fluidFuelBoilerRecipes = new ArrayList<>();

    public static void init() {
        SolidFuelFiredBoilerRecipe.init();
        SteamTurbineRecipe.init();
        FluidFuelBoilerRecipe.init();
    }

    public static void initOredict() {
        for(Map.Entry<Integer,ChemicalElement> element : ElementRegistry.getAllElements().entrySet()) {
            if(element.getValue().getMaterials().contains("ingot")) {
                OreDictionary.registerOre("ingot" + StringsKt.capitalize(element.getValue().getName()),ModItems.INSTANCE.getElementIngot().toStack(element.getKey()));
            }
            if(element.getValue().getMaterials().contains("dust")) {
                OreDictionary.registerOre("dust" + StringsKt.capitalize(element.getValue().getName()),ModItems.INSTANCE.getElementDust().toStack(element.getKey()));
            }
        }
    }

    public static void addSolidFuelFiredBoilerRecipe(int fuel,FluidStack input,FluidStack output) {
        solidFuelFiredBoilerRecipes.add(new SolidFuelFiredBoilerRecipe(fuel,input,output));
    }

    public static void addSteamTurbineRecipe(FluidStack input,int output) {
        steamTurbineRecipes.add(new SteamTurbineRecipe(input,output));
    }

    public static void addFluidFuelBoilerRecipe(FluidStack input,int fuel) {
        fluidFuelBoilerRecipes.add(new FluidFuelBoilerRecipe(input,fuel));
    }
}
