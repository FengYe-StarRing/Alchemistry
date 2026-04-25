package al132.alchemistry.chemistry;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChemicalMixture implements ICompoundComponent {
    public final String name;
    public final Color color;
    public final List<CompoundPair> components;
    public List<String> materials;

    public ChemicalMixture(String name,Color color,List<CompoundPair> components) {
        this.name = name;
        this.color = color;
        this.components = components;
        this.materials = Collections.singletonList("default");
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

    public int getBurnTime() {
        int burnTime = 0;
        for(CompoundPair pair : components) {
            burnTime += pair.getBurnTime();
        }
        return burnTime;
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
}
