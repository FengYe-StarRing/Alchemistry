package al132.alchemistry.multiblock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.block.BlockController;
import hellfirepvp.modularmachinery.common.block.BlockFactoryController;
import hellfirepvp.modularmachinery.common.item.ItemBlockController;
import hellfirepvp.modularmachinery.common.machine.DynamicMachine;
import hellfirepvp.modularmachinery.common.machine.MachineRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.event.RegistryEvent;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public class ModMultiblocks {
    private static final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(DynamicMachine.class,new DynamicMachine.MachineDeserializer())
        .create();

    public static void registerMultiblocks() {
        registerMultiblock("test_1.json");
        registerMultiblock("test_2.json");
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    public static void registerItems(RegistryEvent.Register<Item> event) {

    }

    public static void registerMultiblock(String path) {
        Reader reader = new InputStreamReader(ModMultiblocks.class.getResourceAsStream("/assets/modularmachinery/machinery/" + path));
        DynamicMachine machine = GSON.fromJson(reader,DynamicMachine.class);
        MachineRegistry.registerMachines(Collections.singletonList(machine));
        try {
            Field field = MachineRegistry.class.getDeclaredField("WAIT_FOR_LOAD_MACHINERY");
            field.setAccessible(true);
            Map<ResourceLocation,Tuple<DynamicMachine,String>> map = (Map<ResourceLocation,Tuple<DynamicMachine,String>>)field.get(null);
            map.put(machine.getRegistryName(),new Tuple<>(machine,path));
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
    }

    private static void registerBlock(RegistryEvent.Register<Block> event,String name,BlockController controller) {
        DynamicMachine machine = MachineRegistry.getRegistry().getMachine(new ResourceLocation(ModularMachinery.MODID,name));
        if(machine == null) return;
        BlockController original = BlockController.MACHINE_CONTROLLERS.get(machine);
        if(original == null) return;
        controller.setRegistryName(original.getRegistryName());
        controller.setTranslationKey(original.getTranslationKey());
        event.getRegistry().register(controller);
        BlockController.MACHINE_CONTROLLERS.put(machine,controller);
    }

    private static void registerBlock(RegistryEvent.Register<Block> event,String name,BlockFactoryController controller) {
        DynamicMachine machine = MachineRegistry.getRegistry().getMachine(new ResourceLocation(ModularMachinery.MODID,name));
        if(machine == null) return;
        BlockFactoryController original = BlockFactoryController.FACTORY_CONTROLLERS.get(machine);
        if(original == null) return;
        controller.setRegistryName(original.getRegistryName());
        controller.setTranslationKey(original.getTranslationKey());
        event.getRegistry().register(controller);
        BlockFactoryController.FACTORY_CONTROLLERS.put(machine,controller);
    }

    private static void registerItem(RegistryEvent.Register<Item> event,String name) {
        DynamicMachine machine = MachineRegistry.getRegistry().getMachine(new ResourceLocation(ModularMachinery.MODID,name));
        if(machine == null) return;
        BlockController controller = BlockController.MACHINE_CONTROLLERS.get(machine);
        BlockFactoryController factoryController = BlockFactoryController.FACTORY_CONTROLLERS.get(machine);
        if(controller != null) {
            ItemBlockController item = new ItemBlockController(controller);
            item.setRegistryName(controller.getRegistryName());
            event.getRegistry().register(item);
        }
        if(factoryController != null) {
            ItemBlockController item = new ItemBlockController(factoryController);
            item.setRegistryName(factoryController.getRegistryName());
            event.getRegistry().register(item);
        }
    }
}
