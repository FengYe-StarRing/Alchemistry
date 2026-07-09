package al132.alchemistry.chemistry;

import java.util.HashMap;
import java.util.Map;

public class CompoundPair {
    public final ICompoundComponent component;
    public final int quantity;

    public CompoundPair(String name,int quantity) {
        component = find(name);
        this.quantity = quantity;
    }

    private static ICompoundComponent find(String name) {
        ChemicalElement element = ElementRegistry.get(name);
        Map.Entry<Integer,ChemicalCompound> compound = CompoundRegistry.get(name);
        Map.Entry<Integer,ChemicalMixture> mixture = MixtureRegistry.get(name);
        return compound == null ? (mixture == null ? element : mixture.getValue()) : compound.getValue();
    }

    public String toAbbreviatedString() {
        StringBuilder s1 = new StringBuilder(component.toAbbreviatedString());
        StringBuilder s2 = new StringBuilder();
        switch(quantity) {
            case 0:
                s2.append('*');
                break;
            case -1:
                s2.append('﹖');
                break;
            default:
                if(quantity > 1) {
                    for(char ch : String.valueOf(quantity).toCharArray()) {
                        s2.append((char)(Character.codePointAt("₀",0) + Character.getNumericValue(ch)));
                    }
                }
                break;
        }
        if(component instanceof ChemicalCompound || component instanceof ChemicalMixture) {
            return "(" + s1 + ")" + s2;
        }
        return s1.append(s2).toString();
    }

    public Map<ChemicalElement,Integer> getElementCompose() {
        if(component instanceof ChemicalCompound) {
            return ((ChemicalCompound)component).getElementCompose();
        }
        if(component instanceof ChemicalMixture) {
            return ((ChemicalMixture)component).getElementCompose();
        }
        Map<ChemicalElement,Integer> compose = new HashMap<>();
        if(component instanceof ChemicalElement) {
            compose.put((ChemicalElement)component,quantity);
        }
        return compose;
    }

    public int getBurnTime() {
        if(component instanceof ChemicalElement) {
            return ((ChemicalElement)component).burnTime * Math.abs(quantity);
        }
        if(component instanceof ChemicalCompound) {
            ChemicalCompound compound = (ChemicalCompound)component;
            return compound.getBurnTime();
        }
        if(component instanceof ChemicalMixture) {
            ChemicalMixture mixture = (ChemicalMixture)component;
            return mixture.getBurnTime();
        }
        return 0;
    }

    public boolean isElement() {
        return component instanceof ChemicalElement;
    }
}
