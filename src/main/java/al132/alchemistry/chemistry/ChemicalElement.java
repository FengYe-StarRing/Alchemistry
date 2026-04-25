package al132.alchemistry.chemistry;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChemicalElement implements ICompoundComponent {
    public final String name;
    public final String abbreviation;
    public final Color color;
    public List<String> materials;
    public int burnTime = 0;

    public ChemicalElement(String name,String abbreviation,Color color) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.color = color;
        materials = Collections.singletonList("ingot");
    }

    public ChemicalElement(String name,String abbreviation,Color color,String[] materials) {
        this(name,abbreviation,color);
        this.materials = Arrays.asList(materials);
    }

    @NotNull
    @Override
    public String toAbbreviatedString() {
        return abbreviation;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }
}
