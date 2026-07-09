package al132.alchemistry.blocks;

import al132.alib.blocks.ALBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<ALBlock> blocks = new ArrayList<>();

    public static final BlockSolidFuelFiredBoiler solidFuelFiredBoiler = new BlockSolidFuelFiredBoiler("solid_fuel_fired_boiler");
    public static final BlockSteamTurbine steamTurbine = new BlockSteamTurbine("steam_turbine");
    public static final BlockFluidFuelBoiler fluidFuelBoiler = new BlockFluidFuelBoiler("fluid_fuel_boiler");
    public static final BlockGasTurbine gasTurbine = new BlockGasTurbine("gas_turbine");
    public static final BlockDistillationChamber distillationChamber = new BlockDistillationChamber("distillation_chamber");
    public static final BlockRefineryChamber refineryChamber = new BlockRefineryChamber("refinery_chamber");
    public static final BlockBatteryBox batteryBox = new BlockBatteryBox("battery_box");
    public static final BlockFuelCell fuelCell = new BlockFuelCell("fuel_cell");
    public static final BlockSolidFuelCell solidFuelCell = new BlockSolidFuelCell("solid_fuel_cell");
    public static final BlockReservoir reservoir = new BlockReservoir("reservoir");

    static {
        blocks.add(solidFuelFiredBoiler);
        blocks.add(steamTurbine);
        blocks.add(fluidFuelBoiler);
        blocks.add(gasTurbine);
        blocks.add(distillationChamber);
        blocks.add(refineryChamber);
        blocks.add(batteryBox);
        blocks.add(fuelCell);
        blocks.add(solidFuelCell);
        blocks.add(reservoir);
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(ALBlock block : blocks) {
            block.registerBlock(event);
        }
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(ALBlock block : blocks) {
            block.registerItemBlock(event);
        }
    }
}
