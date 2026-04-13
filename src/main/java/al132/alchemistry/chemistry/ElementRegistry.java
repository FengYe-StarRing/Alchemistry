package al132.alchemistry.chemistry;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ElementRegistry {
    private static final Map<Integer,ChemicalElement> elements = new HashMap<>();

    public static void init() {
        add(1,"hydrogen","H",new Color(0,0,255),new String[]{"fluid"});
        add(2,"helium","He",new Color(255,0,0),new String[]{"fluid"});
        add(3,"lithium","Li",new Color(40,158,86));
        add(4,"beryllium","Be",new Color(184,199,224));
        add(5,"boron","B",new Color(154,176,226));
        add(6,"carbon","C",new Color(59,60,63));
        add(7,"nitrogen","N",new Color(66,123,214),new String[]{"fluid"});
        add(8,"oxygen","O",new Color(229,220,156),new String[]{"fluid"});
        add(9,"fluorine","F",new Color(204,186,55),new String[]{"fluid"});
        add(10,"neon","Ne",new Color(87,229,16),new String[]{"fluid"});
        add(11,"sodium","Na",new Color(211,198,131));
        add(12,"magnesium","Mg",new Color(237,178,173));
        add(13,"aluminum","Al",new Color(247,110,69));
        add(14,"silicon","Si",new Color(173,178,121));
        add(15,"phosphorus","P",new Color(234,98,132));
        add(16,"sulfur","S",new Color(145,158,6),new String[]{"dust"});
        add(17,"chlorine","Cl",new Color(77,102,28),new String[]{"fluid"});
        add(18,"argon","Ar",new Color(119,117,255),new String[]{"fluid"});
        add(19,"potassium","K",new Color(198,152,95));
        add(20,"calcium","Ca",new Color(219,210,199));
        add(21,"scandium","Sc",new Color(252,255,99));
        add(22,"titanium","Ti",new Color(99,255,115));
        add(23,"vanadium","V",new Color(195,186,242));
        add(24,"chromium","Cr",new Color(236,237,218));
        add(25,"manganese","Mn",new Color(225,186,242));
        add(26,"iron","Fe",new Color(128,128,128),new String[]{"none"});
        add(27,"cobalt","Co",new Color(17,114,198));
        add(28,"nickel","Ni",new Color(198,157,162));
        add(29,"copper","Cu",new Color(255,154,30));
        add(30,"zinc","Zn",new Color(189,196,141));
        add(31,"gallium","Ga",new Color(122,20,49));
        add(32,"germanium","Ge",new Color(104,172,255));
        add(33,"arsenic","As",new Color(62,145,76));
        add(34,"selenium","Se",new Color(116,62,145));
        add(35,"bromine","Br",new Color(77,160,0),new String[]{"fluid"});
        add(36,"krypton","Kr",new Color(229,151,50),new String[]{"fluid"});
        add(37,"rubidium","Rb",new Color(15,61,40));
        add(38,"strontium","Sr",new Color(206,88,24));
        add(39,"yttrium","Y",new Color(206,179,24));
        add(40,"zirconium","Zr",new Color(127,80,22));
        add(41,"niobium","Nb",new Color(2,29,255));
        add(42,"molybdenum","Mo",new Color(39,0,48));
        add(43,"technetium","Tc",new Color(72,170,63));
        add(44,"ruthenium","Ru",new Color(255,240,86));
        add(45,"rhodium","Rh",new Color(255,0,80));
        add(46,"palladium","Pd",new Color(0,255,169));
        add(47,"silver","Ag",new Color(226,217,206));
        add(48,"cadmium","Cd",new Color(160,147,115));
        add(49,"indium","In",new Color(163,230,255));
        add(50,"tin","Sn",new Color(132,161,206));
        add(51,"antimony","Sb",new Color(193,40,58));
        add(52,"tellurium","Te",new Color(39,91,26));
        add(53,"iodine","I",new Color(62,17,63),new String[]{"fluid"});
        add(54,"xenon","Xe",new Color(196,51,204),new String[]{"fluid"});
        add(55,"cesium","Cs",new Color(255,148,0));
        add(56,"barium","Ba",new Color(0,219,179));
        add(57,"lanthanum","La",new Color(188,253,255));
        add(58,"cerium","Ce",new Color(255,254,211));
        add(59,"praseodymium","Pr",new Color(255,161,0));
        add(60,"neodymium","Nd",new Color(38,28,11));
        add(61,"promethium","Pm",new Color(105,175,123));
        add(62,"samarium","Sm",new Color(73,69,73));
        add(63,"europium","Eu",new Color(27,211,45));
        add(64,"gadolinium","Gd",new Color(123,50,208));
        add(65,"terbium","Tb",new Color(3,37,118));
        add(66,"dysprosium","Dy",new Color(73,0,219));
        add(67,"holmium","Ho",new Color(62,255,56));
        add(68,"erbium","Er",new Color(194,214,215));
        add(69,"thulium","Tm",new Color(234,178,178));
        add(70,"ytterbium","Yb",new Color(255,76,219));
        add(71,"lutetium","Lu",new Color(175,0,219));
        add(72,"hafnium","Hf",new Color(69,81,233));
        add(73,"tantalum","Ta",new Color(108,142,110));
        add(74,"tungsten","W",new Color(120,128,140));
        add(75,"rhenium","Re",new Color(199,226,89));
        add(76,"osmium","Os",new Color(102,129,173));
        add(77,"iridium","Ir",new Color(215,242,238));
        add(78,"platinum","Pt",new Color(114,202,229));
        add(79,"gold","Au",new Color(255,255,0),new String[]{"none"});
        add(80,"mercury","Hg",new Color(160,159,157),new String[]{"fluid"});
        add(81,"thallium","Tl",new Color(103,50,25));
        add(82,"lead","Pb",new Color(186,135,193));
        add(83,"bismuth","Bi",new Color(252,171,40));
        add(84,"polonium","Po",new Color(138,87,85));
        add(85,"astatine","At",new Color(120,128,213));
        add(86,"radon","Rn",new Color(76,66,179),new String[]{"fluid"});
        add(87,"francium","Fr",new Color(81,114,198));
        add(88,"radium","Ra",new Color(255,181,221));
        add(89,"actinium","Ac",new Color(14,182,145));
        add(90,"thorium","Th",new Color(56,79,75));
        add(91,"protactinium","Pa",new Color(204,233,2));
        add(92,"uranium","U",new Color(93,178,19));
        add(93,"neptunium","Np",new Color(32,20,158));
        add(94,"plutonium","Pu",new Color(211,211,209));
        add(95,"americium","Am",new Color(237,124,75));
        add(96,"curium","Cm",new Color(229,110,149));
        add(97,"berkelium","Bk",new Color(44,66,49));
        add(98,"californium","Cf",new Color(175,182,16));
        add(99,"einsteinium","Es",new Color(192,210,95));
        add(100,"fermium","Fm",new Color(74,226,83));
        add(101,"mendelevium","Md",new Color(175,176,249));
        add(102,"nobelium","No",new Color(94,44,52));
        add(103,"lawrencium","Lr",new Color(216,45,92));
        add(104,"rutherfordium","Rf",new Color(240,61,22));
        add(105,"dubnium","Db",new Color(11,112,108));
        add(106,"seaborgium","Sg",new Color(158,49,74));
        add(107,"bohrium","Bh",new Color(166,251,51));
        add(108,"hassium","Hs",new Color(78,5,51));
        add(109,"meitnerium","Mt",new Color(169,138,37));
        add(110,"darmstadtium","Ds",new Color(14,144,190));
        add(111,"roentgenium","Rg",new Color(150,90,90));
        add(112,"copernicium","Cn",new Color(160,40,240));
        add(113,"nihonium","Nh",new Color(220,250,180));
        add(114,"flerovium","Fl",new Color(200,180,254));
        add(115,"moscovium","Mc",new Color(250,180,200));
        add(116,"livermorium","Lv",new Color(250,250,200));
        add(117,"tennessine","Ts",new Color(150,250,250));
        add(118,"oganesson","Og",new Color(250,150,250),new String[]{"fluid"});
    }

    public static ChemicalElement get(int number) {
        return elements.get(number);
    }

    public static ChemicalElement get(String name) {
        for(ChemicalElement element : elements.values()) {
            if(element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }

    public static void add(int atomicNumber,String name,String abbreviation,Color color) {
        add(atomicNumber,name,abbreviation,color,new String[]{"ingot"});
    }

    public static void add(int atomicNumber,String name,String abbreviation,Color color,String[] materials) {
        ChemicalElement newElement = new ChemicalElement(name,abbreviation,color,materials);
        if(elements.containsKey(atomicNumber)) {
            elements.replace(atomicNumber,newElement);
        } else {
            elements.put(atomicNumber,newElement);
        }
    }

    public static int getMeta(String name) {
        for(Map.Entry<Integer,ChemicalElement> entry : elements.entrySet()) {
            if(entry.getValue().getName().equals(name)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static Map<Integer,ChemicalElement> getAllElements() {
        return elements;
    }

    public static Set<Integer> keys() {
        return elements.keySet();
    }

    public static Set<Map.Entry<Integer,ChemicalElement>> get(String[] materials) {
        Map<Integer,ChemicalElement> ret = new HashMap<>();
        for(Integer key : keys()) {
            for(String material : materials) {
                ChemicalElement element = elements.get(key);
                if(element.getMaterials().contains(material)) {
                    ret.put(key,element);
                }
            }
        }
        return ret.entrySet();
    }
}
