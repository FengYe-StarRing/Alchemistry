package al132.alchemistry.chemistry;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CompoundRegistry {
    private static final Map<Integer,ChemicalCompound> compounds = new HashMap<>();
    private static int internalChemicalIndex = 0;

    public static void init() {
        addCompound("steam",Color.white,new CompoundPair[]{
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
        addCompound("carbon_dioxide",new Color(100,80,253),new CompoundPair[]{
                new CompoundPair("carbon",1),
                new CompoundPair("oxygen",2)
        },new String[]{"fluid"});
        addCompound("methane",new Color(200,30,180),new CompoundPair[]{
                new CompoundPair("carbon",1),
                new CompoundPair("hydrogen",4)
        },new String[]{"fluid"});
        addCompound("ethane",new Color(200,30,50),new CompoundPair[]{
                new CompoundPair("carbon",2),
                new CompoundPair("hydrogen",6)
        },new String[]{"fluid"});
        addCompound("propane",new Color(100,30,50),new CompoundPair[]{
                new CompoundPair("carbon",3),
                new CompoundPair("hydrogen",8)
        },new String[]{"fluid"});
        addCompound("butane",new Color(111,150,180),new CompoundPair[]{
                new CompoundPair("carbon",4),
                new CompoundPair("hydrogen",10)
        },new String[]{"fluid"});
        addCompound("pentane",new Color(111,150,85),new CompoundPair[]{
                new CompoundPair("carbon",5),
                new CompoundPair("hydrogen",12)
        },new String[]{"fluid"});
        addCompound("hexane",new Color(111,205,50),new CompoundPair[]{
                new CompoundPair("carbon",6),
                new CompoundPair("hydrogen",14)
        },new String[]{"fluid"});
        addCompound("heptane",new Color(90,180,60),new CompoundPair[]{
                new CompoundPair("carbon",7),
                new CompoundPair("hydrogen",16)
        },new String[]{"fluid"});
        addCompound("octane",new Color(70,200,80),new CompoundPair[]{
                new CompoundPair("carbon",8),
                new CompoundPair("hydrogen",18)
        },new String[]{"fluid"});
        addCompound("nonane",new Color(50,220,100),new CompoundPair[]{
                new CompoundPair("carbon",9),
                new CompoundPair("hydrogen",20)
        },new String[]{""});
        addCompound("decane",new Color(30,240,120),new CompoundPair[]{
                new CompoundPair("carbon",10),
                new CompoundPair("hydrogen",22)
        },new String[]{""});
        addCompound("undecane",new Color(20,200,140),new CompoundPair[]{
                new CompoundPair("carbon",11),
                new CompoundPair("hydrogen",24)
        },new String[]{""});
        addCompound("dodecane",new Color(20,180,160),new CompoundPair[]{
                new CompoundPair("carbon",12),
                new CompoundPair("hydrogen",26)
        },new String[]{""});
        addCompound("tridecane",new Color(20,160,180),new CompoundPair[]{
                new CompoundPair("carbon",13),
                new CompoundPair("hydrogen",28)
        },new String[]{""});
        addCompound("tetradecane",new Color(20,140,200),new CompoundPair[]{
                new CompoundPair("carbon",14),
                new CompoundPair("hydrogen",30)
        },new String[]{""});
        addCompound("pentadecane",new Color(20,120,220),new CompoundPair[]{
                new CompoundPair("carbon",15),
                new CompoundPair("hydrogen",32)
        },new String[]{""});
        addCompound("hexadecane",new Color(20,100,240),new CompoundPair[]{
                new CompoundPair("carbon",16),
                new CompoundPair("hydrogen",34)
        },new String[]{""});
        addCompound("heptadecane",new Color(40,80,200),new CompoundPair[]{
                new CompoundPair("carbon",17),
                new CompoundPair("hydrogen",36)
        });
        addCompound("octadecane",new Color(60,60,160),new CompoundPair[]{
                new CompoundPair("carbon",18),
                new CompoundPair("hydrogen",38)
        });
        addCompound("nonadecane",new Color(80,40,120),new CompoundPair[]{
                new CompoundPair("carbon",19),
                new CompoundPair("hydrogen",40)
        });
        addCompound("eicosane",new Color(100,20,80),new CompoundPair[]{
                new CompoundPair("carbon",20),
                new CompoundPair("hydrogen",42)
        });
        addCompound("ethylene",new Color(220,50,30),new CompoundPair[]{
                new CompoundPair("carbon",2),
                new CompoundPair("hydrogen",4)
        },new String[]{"fluid"});
        addCompound("propylene",new Color(200,60,40),new CompoundPair[]{
                new CompoundPair("carbon",3),
                new CompoundPair("hydrogen",6)
        },new String[]{"fluid"});
        addCompound("butadiene",new Color(200,40,60),new CompoundPair[]{
                new CompoundPair("carbon",4),
                new CompoundPair("hydrogen",6)
        },new String[]{"fluid"});
        addCompound("butene",new Color(180,80,60),new CompoundPair[]{
                new CompoundPair("carbon",4),
                new CompoundPair("hydrogen",8)
        },new String[]{"fluid"});
        addCompound("pentene",new Color(160,100,50),new CompoundPair[]{
                new CompoundPair("carbon",5),
                new CompoundPair("hydrogen",10)
        },new String[]{"fluid"});
        addCompound("hexene",new Color(140,140,40),new CompoundPair[]{
                new CompoundPair("carbon",6),
                new CompoundPair("hydrogen",12)
        },new String[]{"fluid"});
        addCompound("heptene",new Color(120,180,60),new CompoundPair[]{
                new CompoundPair("carbon",7),
                new CompoundPair("hydrogen",14)
        },new String[]{"fluid"});
        addCompound("octene",new Color(100,220,60),new CompoundPair[]{
                new CompoundPair("carbon",8),
                new CompoundPair("hydrogen",16)
        },new String[]{"fluid"});
        addCompound("benzene",new Color(120,80,160),new CompoundPair[]{
                new CompoundPair("carbon",6),
                new CompoundPair("hydrogen",6)
        },new String[]{"fluid"});
        addCompound("toluene",new Color(100,120,60),new CompoundPair[]{
                new CompoundPair("carbon",7),
                new CompoundPair("hydrogen",8)
        },new String[]{"fluid"});
        addCompound("hydrogen_sulfide",new Color(180,180,40),new CompoundPair[]{
                new CompoundPair("hydrogen",2),
                new CompoundPair("sulfur",1)
        },new String[]{"fluid"});
        addCompound("distilled_water",new Color(17,94,192),new CompoundPair[]{
                new CompoundPair("hydrogen",2),
                new CompoundPair("oxygen",1)
        },new String[]{"fluid"});
        addCompound("lithium_sulfide",new Color(255,255,255),new CompoundPair[]{
            new CompoundPair("lithium",2),
            new CompoundPair("sulfur",1)
        },new String[]{"dust"});
        addCompound("lithium_selenide",new Color(94,21,21),new CompoundPair[]{
            new CompoundPair("lithium",2),
            new CompoundPair("selenium",1)
        },new String[]{"dust"});
        addCompound("sodium_sulfide",new Color(200,200,150),new CompoundPair[]{
            new CompoundPair("sodium",2),
            new CompoundPair("sulfur",1)
        },new String[]{"dust"});
        addCompound("sodium_selenide",new Color(180,120,100),new CompoundPair[]{
            new CompoundPair("sodium",2),
            new CompoundPair("selenium",1)
        },new String[]{"dust"});
        addCompound("magnesium_sulfide",new Color(160,140,120),new CompoundPair[]{
            new CompoundPair("magnesium",1),
            new CompoundPair("sulfur",1)
        },new String[]{"dust"});
        addCompound("calcium_sulfide",new Color(220,210,180),new CompoundPair[]{
            new CompoundPair("calcium",1),
            new CompoundPair("sulfur",1)
        },new String[]{"dust"});
    }

    public static ChemicalCompound addCompound(int meta,String name,Color color,List<CompoundPair> components,String[] materials) {
        ChemicalCompound compound = new ChemicalCompound(name,color,components,Arrays.asList(materials));
        compounds.put(meta,compound);
        return compound;
    }

    public static ChemicalCompound addCompound(String name,Color color,List<CompoundPair> components,String[] materials) {
        return addCompound(internalChemicalIndex++,name,color,components,materials);
    }

    public static ChemicalCompound addCompound(String name,Color color,CompoundPair[] components,String[] materials) {
        return addCompound(name,color,Arrays.asList(components),materials);
    }

    public static void addCompound(String name,Color color,CompoundPair[] components) {
        addCompound(name,color,components,new String[]{"default"});
    }

    public static Map.Entry<Integer,ChemicalCompound> get(String name) {
        for (Map.Entry<Integer,ChemicalCompound> compound : compounds.entrySet()) {
            if(compound.getValue().name.equals(name)) {
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
                if(compound.materials.contains(material)) {
                    ret.put(key,compound);
                }
            }
        }
        return ret.entrySet();
    }
}
