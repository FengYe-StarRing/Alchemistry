package al132.alchemistry.compat.ct;

import al132.alchemistry.chemistry.*;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.mc1120.item.MCItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.alchemistry.Util")
@ModOnly("alchemistry")
@ZenRegister
public class CTUtil {
    @ZenMethod
    public static void createElement(int atomicNumber,String name,String abbreviation,int red,int green,int blue) {
        createElement(atomicNumber,name,abbreviation,red,green,blue,new String[]{"ingot"});
    }

    @ZenMethod
    public static void createElement(int atomicNumber,String name,String abbreviation,int red,int green,int blue,String[] materials) {
        String parsedName = name.trim().toLowerCase().replace(" ","_");
        CraftTweakerAPI.apply(new IAction() {
            @Override
            public String describe() {
                return "Added new chemical element [$atomicNumber,$parsedName,$abbreviation]"
                        .replaceAll("\\$atomicNumber",abbreviation)
                        .replaceAll("\\$parsedName",parsedName)
                        .replaceAll("\\$abbreviation",abbreviation);
            }
            @Override
            public void apply() {
                ElementRegistry.add(atomicNumber,name,abbreviation,new Color(red,green,blue),materials);
            }
        });
    }

    @ZenMethod
    public static void createCompound(int meta,String name,int red,int green,int blue,Object[][] components) {
        createCompound(meta,name,red,green,blue,components,new String[]{"default"});
    }

    @ZenMethod
    public static void createCompound(int meta,String name,int red,int green,int blue,Object[][] components,String[] materials) {
        String parsedName = name.trim().toLowerCase().replace(" ","_");
        CraftTweakerAPI.apply(new IAction() {
            @Override
            public String describe() {
                return "Added new chemical compound [$parsedName]".replaceAll("\\$parsedName",parsedName);
            }
            @Override
            public void apply() {
                List<CompoundPair> parsedComponents = new ArrayList<>();
                for (Object[] x : components) {
                    String name = ((String) x[0]).toLowerCase().replace(" ", "_");
                    int amount = (Integer) x[1];
                    parsedComponents.add(new CompoundPair(name,amount));
                }
                CompoundRegistry.addCompound(meta,parsedName,new Color(red,green,blue),parsedComponents,materials);
            }
        });
    }
}
