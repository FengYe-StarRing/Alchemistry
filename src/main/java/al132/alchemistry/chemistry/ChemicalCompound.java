package al132.alchemistry.chemistry;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChemicalCompound implements ICompoundComponent {
    public final String name;
    public final Color color;
    public final List<CompoundPair> components;
    public List<String> materials;

    private int batteryCapacity = -1;

    public ChemicalCompound(String name,Color color,List<CompoundPair> components,List<String> materials) {
        this.name = name;
        this.color = color;
        this.components = components;
        this.materials = materials;
    }

    @NotNull
    @Override
    public String toAbbreviatedString() {
        StringBuilder builder = new StringBuilder();
        for(CompoundPair pair : components) {
            builder.append(pair.toAbbreviatedString());
        }
        return builder.toString();
    }

    public Map<ChemicalElement,Integer> getElementCompose() {
        Map<ChemicalElement,Integer> compose = new HashMap<>();
        for(CompoundPair pair : components) {
            for(Map.Entry<ChemicalElement,Integer> entry : pair.getElementCompose().entrySet()) {
                compose.merge(entry.getKey(),entry.getValue(),Math::addExact);
            }
        }
        return compose;
    }

    public int getBurnTime() {
        if(!hasElement("carbon") || !hasElement("hydrogen")) {
            return 0;
        }
        int burnTime = 0;
        for(CompoundPair pair : components) {
            burnTime += pair.getBurnTime();
        }
        return burnTime;
    }

    public boolean hasElement(String name) {
        return getElementCompose().containsKey(ElementRegistry.get(name));
    }

    public int getBatteryCapacity() {
        if(batteryCapacity != -1) {
            return batteryCapacity;
        }
        if(components.size() == 2) {
            CompoundPair pair1 = components.get(0);
            CompoundPair pair2 = components.get(1);
            if(pair1.isElement() && pair2.isElement()) {
                ChemicalElement element1 = (ChemicalElement)pair1.component;
                ChemicalElement element2 = (ChemicalElement)pair2.component;
                CompoundPair cathode = null;
                CompoundPair anode = null;
                if(element1.canBeCathode()) cathode = pair1;
                if(element2.canBeCathode()) cathode = pair2;
                if(element1.canBeAnode()) anode = pair1;
                if(element2.canBeAnode()) anode = pair2;
                if(cathode == null || anode == null) {
                    batteryCapacity = 0;
                    return 0;
                }
                ChemicalElement cathodeElement = (ChemicalElement)cathode.component;
                ChemicalElement anodeElement = (ChemicalElement)anode.component;
                int energy = ElementRegistry.CARBON_BURN_ENERGY / ElementRegistry.CARBON.getOuterElectron();
                batteryCapacity =  Math.abs(cathodeElement.getVoltageFactor() - anodeElement.getVoltageFactor()) * cathode.quantity * cathodeElement.getOuterElectron() * energy;
                return batteryCapacity;
            }
        }
        return 0;
    }
}
