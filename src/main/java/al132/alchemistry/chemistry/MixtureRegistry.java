package al132.alchemistry.chemistry;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MixtureRegistry {
    private static final Map<Integer,ChemicalMixture> mixtures = new HashMap<>();
    private static int index = 0;

    public static void init() {
        addMixture("oil",Color.black,new CompoundPair[]{
                new CompoundPair("carbon",-1),
                new CompoundPair("hydrogen",-1),
                new CompoundPair("sulfur",-1),
                new CompoundPair("oxygen",-1),
                new CompoundPair("nitrogen",-1)
        },new String[]{"fluid"});
        addMixture("natural_gas",Color.white,new CompoundPair[]{
                new CompoundPair("carbon",-1),
                new CompoundPair("hydrogen",-1),
                new CompoundPair("sulfur",-1)
        },new String[]{"fluid"});
        addMixture("refinery_gas",new Color(180,180,200),new CompoundPair[]{
                new CompoundPair("methane",-1),
                new CompoundPair("ethane",-1),
                new CompoundPair("propane",-1),
                new CompoundPair("butane",-1)
        },new String[]{"fluid"});
        addMixture("naphtha",new Color(220,220,180),new CompoundPair[]{
                new CompoundPair("pentane",-1),
                new CompoundPair("hexane",-1),
                new CompoundPair("heptane",-1),
                new CompoundPair("octane",-1)
        },new String[]{"fluid"});
        addMixture("light_fuel",new Color(200,180,120),new CompoundPair[]{
                new CompoundPair("nonane",-1),
                new CompoundPair("decane",-1),
                new CompoundPair("undecane",-1),
                new CompoundPair("dodecane",-1)
        },new String[]{"fluid"});
        addMixture("heavy_fuel",new Color(80,60,40),new CompoundPair[]{
                new CompoundPair("tridecane",-1),
                new CompoundPair("tetradecane",-1),
                new CompoundPair("pentadecane",-1),
                new CompoundPair("hexadecane",-1)
        },new String[]{"fluid"});
        addMixture("oil_residue",new Color(30,30,30),new CompoundPair[]{
                new CompoundPair("carbon",-1),
                new CompoundPair("hydrogen",-1),
                new CompoundPair("sulfur",-1),
                new CompoundPair("oxygen",-1),
                new CompoundPair("nitrogen",-1)
        });
        addMixture("jet_a",new Color(140,120,80),new CompoundPair[]{
                new CompoundPair("light_fuel",1),
                new CompoundPair("heavy_fuel",1),
        },new String[]{"fluid"});
        addMixture("jet_b",new Color(180,170,130),new CompoundPair[]{
                new CompoundPair("naphtha",1),
                new CompoundPair("light_fuel",1),
                new CompoundPair("heavy_fuel",1),
        },new String[]{"fluid"});
        addMixture("liquefied_petroleum_gas",new Color(106,90,115),new CompoundPair[]{
                new CompoundPair("propane",1),
                new CompoundPair("butane",1),
        },new String[]{"fluid"});
    }

    public static void addMixture(String name,Color color,CompoundPair[] components) {
        addMixture(name,color,components,new String[]{"default"});
    }

    public static void addMixture(String name,Color color,CompoundPair[] components,String[] materials) {
        ChemicalMixture mixture = new ChemicalMixture(name,color,Arrays.asList(components));
        mixtures.put(index,mixture);
        mixture.materials = Arrays.asList(materials);
        index++;
    }

    public static Map<Integer,ChemicalMixture> getMixtures() {
        return mixtures;
    }

    public static Set<Map.Entry<Integer,ChemicalMixture>> get(String[] materials) {
        Map<Integer,ChemicalMixture> ret = new HashMap<>();
        for(Map.Entry<Integer,ChemicalMixture> entry : mixtures.entrySet()) {
            for(String material : materials) {
                if(entry.getValue().materials.contains(material)) {
                    ret.put(entry.getKey(),entry.getValue());
                    break;
                }
            }
        }
        return ret.entrySet();
    }

    public static ChemicalMixture get(int meta) {
        return mixtures.get(meta);
    }

    public static Map.Entry<Integer,ChemicalMixture> get(String name) {
        for(Map.Entry<Integer,ChemicalMixture> mixture : mixtures.entrySet()) {
            if(mixture.getValue().name.equals(name)) {
                return mixture;
            }
        }
        return null;
    }
}
