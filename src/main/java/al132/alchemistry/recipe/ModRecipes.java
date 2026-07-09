package al132.alchemistry.recipe;

import al132.alchemistry.chemistry.ChemicalElement;
import al132.alchemistry.chemistry.ElementRegistry;
import al132.alchemistry.items.ModItems;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hellfirepvp.modularmachinery.common.crafting.MachineRecipe;
import hellfirepvp.modularmachinery.common.crafting.RecipeRegistry;
import hellfirepvp.modularmachinery.common.crafting.adapter.RecipeAdapterAccessor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.machine.DynamicMachine;
import hellfirepvp.modularmachinery.common.machine.MachineRegistry;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import kotlin.text.StringsKt;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModRecipes {
    public static final List<SolidFuelFiredBoilerRecipe> solidFuelFiredBoilerRecipes = new ArrayList<>();
    public static final List<SteamTurbineRecipe> steamTurbineRecipes = new ArrayList<>();
    public static final List<FluidFuelBoilerRecipe> fluidFuelBoilerRecipes = new ArrayList<>();
    public static final List<GasTurbineRecipe> gasTurbineRecipes = new ArrayList<>();
    public static final List<RefineryChamberRecipe> refineryChamberRecipes = new ArrayList<>();
    public static final List<DistillationChamberRecipe> distillationChamberRecipes = new ArrayList<>();
    public static final List<FuelCellRecipe> fuelCellRecipes = new ArrayList<>();
    public static final List<SolidFuelCellRecipe> solidFuelCellRecipes = new ArrayList<>();

    private static final Gson GSON = new GsonBuilder()
        .registerTypeHierarchyAdapter(MachineRecipe.MachineRecipeContainer.class,new MachineRecipe.Deserializer())
        .registerTypeHierarchyAdapter(ComponentRequirement.class,new MachineRecipe.ComponentDeserializer())
        .registerTypeHierarchyAdapter(RecipeAdapterAccessor.class,new RecipeAdapterAccessor.Deserializer())
        .registerTypeHierarchyAdapter(RecipeModifier.class,new RecipeModifier.Deserializer())
        .create();

    public static void postInit() {
        SolidFuelFiredBoilerRecipe.init();
        SteamTurbineRecipe.init();
        FluidFuelBoilerRecipe.init();
        GasTurbineRecipe.init();
        RefineryChamberRecipe.init();
        DistillationChamberRecipe.init();
        FuelCellRecipe.init();
        SolidFuelCellRecipe.init();

        addMultiblockRecipe("steam.json");
    }

    public static void initOredict() {
        for(Map.Entry<Integer,ChemicalElement> element : ElementRegistry.getAllElements().entrySet()) {
            if(element.getValue().materials.contains("ingot")) {
                OreDictionary.registerOre("ingot" + StringsKt.capitalize(element.getValue().name),ModItems.INSTANCE.getElementIngot().toStack(element.getKey()));
            }
            if(element.getValue().materials.contains("dust")) {
                OreDictionary.registerOre("dust" + StringsKt.capitalize(element.getValue().name),ModItems.INSTANCE.getElementDust().toStack(element.getKey()));
            }
        }
    }

    public static void addSolidFuelFiredBoilerRecipe(int fuel,FluidStack input,FluidStack output) {
        solidFuelFiredBoilerRecipes.add(new SolidFuelFiredBoilerRecipe(fuel,input,output));
    }

    public static void addSteamTurbineRecipe(FluidStack input,FluidStack output,int energy) {
        steamTurbineRecipes.add(new SteamTurbineRecipe(input,output,energy));
    }

    public static void addFluidFuelBoilerRecipe(FluidStack input,int fuel) {
        fluidFuelBoilerRecipes.add(new FluidFuelBoilerRecipe(input,fuel));
    }

    public static void addGasTurbineRecipe(FluidStack input,int energy) {
        gasTurbineRecipes.add(new GasTurbineRecipe(input,energy));
    }

    public static void addRefineryChamberRecipe(int energy,int tick,FluidStack input1,FluidStack input2,FluidStack petroleumGas,FluidStack lightFraction,FluidStack middleDistillate,FluidStack heavyFraction,ItemStack oilResidue) {
        refineryChamberRecipes.add(new RefineryChamberRecipe(energy,tick,input1,input2,petroleumGas,lightFraction,middleDistillate,heavyFraction,oilResidue));
    }

    public static void addFuelCellRecipe(FluidStack input1,FluidStack input2,int energy) {
        fuelCellRecipes.add(new FuelCellRecipe(input1,input2,energy));
    }

    public static void addSolidFuelCellRecipe(ItemStack fuel,FluidStack oxygen,int energy,int tick) {
        solidFuelCellRecipes.add(new SolidFuelCellRecipe(fuel,oxygen,energy,tick));
    }

    public static void addDistillationChamberRecipe(int energy,int tick,FluidStack fluidInput,ItemStack itemInput,FluidStack fluidOutput,ItemStack itemOutput) {
        distillationChamberRecipes.add(new DistillationChamberRecipe(energy,tick,fluidInput,itemInput,fluidOutput,itemOutput));
    }

    public static void addMultiblockRecipe(String path) {
        Map<DynamicMachine,List<MachineRecipe>> map = new HashMap<>();
        Reader reader = new InputStreamReader(ModRecipes.class.getResourceAsStream("/assets/modularmachinery/recipes/" + path));
        MachineRecipe.MachineRecipeContainer container = GSON.fromJson(reader,MachineRecipe.MachineRecipeContainer.class);
        for (MachineRecipe recipe : container.getRecipes()) {
            DynamicMachine machine = MachineRegistry.getRegistry().getMachine(recipe.getOwningMachine().getRegistryName());
            if(machine != null) {
                map.computeIfAbsent(machine,k -> new ArrayList<>()).add(recipe);
            }
        }
        RecipeRegistry.registerRecipes(map);
    }
}
