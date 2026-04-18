package al132.alchemistry.blocks;

import al132.alib.blocks.ALBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<ALBlock> blocks = new ArrayList<>();

    public static final BlockSolidFuelFiredBoiler solidBoiler = new BlockSolidFuelFiredBoiler("solid_fuel_fired_boiler");
    public static final BlockSteamTurbine steamTurbine = new BlockSteamTurbine("steam_turbine");
    public static final BlockFluidFuelBoiler fluidFuelBoiler = new BlockFluidFuelBoiler("fluid_fuel_boiler");

    static {
        blocks.add(solidBoiler);
        blocks.add(steamTurbine);
        blocks.add(fluidFuelBoiler);
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
