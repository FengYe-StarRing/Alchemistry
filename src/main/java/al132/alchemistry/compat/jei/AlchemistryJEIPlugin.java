package al132.alchemistry.compat.jei;

import al132.alchemistry.Reference;
import al132.alchemistry.blocks.ModBlocks;
import al132.alchemistry.recipe.ModRecipes;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class AlchemistryJEIPlugin implements IModPlugin {
    public static final String SOLID_FUEL_FIRED_BOILER = Reference.MODID + ".solid_fuel_fired_boiler";
    public static final String FLUID_BOILER_FUEL = Reference.MODID + ".boiler_fuel";
    public static final String STEAM_TURBINE = Reference.MODID + ".steam_turbine";
    public static final String GAS_TURBINE = Reference.MODID + ".gas_turbine";
    public static final String REFINERY_CHAMBER = Reference.MODID + ".refinery_chamber";
    public static final String FUEL_CELL = Reference.MODID + ".fuel_cell";
    public static final String SOLID_FUEL_CELL = Reference.MODID + ".solid_fuel_cell";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new FluidFuelBoilerRecipeCategory(guiHelper));
        registry.addRecipeCategories(new SolidFuelFiredBoilerRecipeCategory(guiHelper));
        registry.addRecipeCategories(new SteamTurbineRecipeCategory(guiHelper));
        registry.addRecipeCategories(new GasTurbineRecipeCategory(guiHelper));
        registry.addRecipeCategories(new RefineryChamberRecipeCategory(guiHelper));
        registry.addRecipeCategories(new FuelCellRecipeCategory(guiHelper));
        registry.addRecipeCategories(new SolidFuelCellRecipeCategory(guiHelper));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(ModRecipes.solidFuelFiredBoilerRecipes,SOLID_FUEL_FIRED_BOILER);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.solidFuelFiredBoiler),SOLID_FUEL_FIRED_BOILER);

        registry.addRecipes(ModRecipes.fluidFuelBoilerRecipes,FLUID_BOILER_FUEL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.fluidFuelBoiler),FLUID_BOILER_FUEL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.fluidFuelBoiler),SOLID_FUEL_FIRED_BOILER);

        registry.addRecipes(ModRecipes.steamTurbineRecipes,STEAM_TURBINE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.steamTurbine),STEAM_TURBINE);

        registry.addRecipes(ModRecipes.gasTurbineRecipes,GAS_TURBINE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.gasTurbine),GAS_TURBINE);

        registry.addRecipes(ModRecipes.refineryChamberRecipes,REFINERY_CHAMBER);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.refineryChamber),REFINERY_CHAMBER);

        registry.addRecipes(ModRecipes.fuelCellRecipes,FUEL_CELL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.fuelCell),FUEL_CELL);

        registry.addRecipes(ModRecipes.solidFuelCellRecipes,SOLID_FUEL_CELL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.solidFuelCell),SOLID_FUEL_CELL);
    }
}
