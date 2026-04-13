package al132.alchemistry.chemistry;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CompoundRegistry {
    private static final Map<Integer,ChemicalCompound> compounds = new HashMap<>();
    private static int internalChemicalIndex = 0;

    public static void init() {
        addCompound("steam",new Color(255,255,255),new CompoundPair[]{
                new CompoundPair("hydrogen",2),
                new CompoundPair("oxygen",1)
        },new String[]{"fluid"});
        addCompound("silicon_dioxide",new Color(165,138,45),new CompoundPair[]{
                new CompoundPair("silicon",1),
                new CompoundPair("oxygen",2)
        },new String[]{"dust"});
        addCompound("sodium_chloride",new Color(219,201,216),new CompoundPair[]{
                new CompoundPair("sodium",1),
                new CompoundPair("chlorine",1)
        },new String[]{"dust"});
    }

    public static void addCompound(int meta,String name,Color color,List<CompoundPair> components,String[] materials) {
        compounds.put(meta,new ChemicalCompound(name,color,false,meta,false,components,false,Arrays.asList(materials)));
    }

    public static void addCompound(String name,Color color,List<CompoundPair> components,String[] materials) {
        addCompound(internalChemicalIndex,name,color,components,materials);
        internalChemicalIndex++;
    }

    public static void addCompound(String name,Color color,CompoundPair[] components,String[] materials) {
        addCompound(name,color,Arrays.asList(components),materials);
    }

    public static void addCompound(String name,Color color,CompoundPair[] components) {
        addCompound(name,color,components,new String[]{"default"});
    }

    public static Map.Entry<Integer,ChemicalCompound> get(String name) {
        for (Map.Entry<Integer,ChemicalCompound> compound : compounds.entrySet()) {
            if(compound.getValue().getName().equals(name)) {
                return compound;
            }
        }
        return null;
    }

    public static ChemicalCompound get(int meta) {
        return compounds.get(meta);
    }

    public static Set<Integer> keys() {
        return compounds.keySet();
    }

    public static Map<Integer,ChemicalCompound> getAllCompounds() {
        return compounds;
    }

    public static Set<Map.Entry<Integer,ChemicalCompound>> get(String[] materials) {
        Map<Integer,ChemicalCompound> ret = new HashMap<>();
        for(Integer key : keys()) {
            for(String material : materials) {
                ChemicalCompound compound = compounds.get(key);
                if(compound.getMaterials().contains(material)) {
                    ret.put(key,compound);
                }
            }
        }
        return ret.entrySet();
    }
}
