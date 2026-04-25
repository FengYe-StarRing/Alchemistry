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
}
