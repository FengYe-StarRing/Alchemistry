package al132.alchemistry.chemistry;

import al132.alchemistry.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChemicalElement implements ICompoundComponent {
    private String name;
    private final String abbreviation;
    private Color color;
    private final Item item;
    private final int meta;
    private List<String> materials;

    public ChemicalElement(String name,String abbreviation,Color color) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.color = color;
        item = ModItems.INSTANCE.getElements();
        meta = ElementRegistry.getMeta(name);
        materials = Collections.singletonList("ingot");
    }

    public ChemicalElement(String name,String abbreviation,Color color,String[] materials) {
        this(name,abbreviation,color);
        this.materials = Arrays.asList(materials);
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String toAbbreviatedString() {
        return abbreviation;
    }

    @NotNull
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(@NotNull Color color) {
        this.color = color;
    }

    @NotNull
    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public int getMeta() {
        return meta;
    }

    @NotNull
    @Override
    public ItemStack toItemStack(int quantity) {
        return new ItemStack(item,quantity,meta);
    }

    @NotNull
    @Override
    public List<String> getMaterials() {
        return materials;
    }

    @Override
    public void setMaterials(@NotNull List<String> materials) {
        this.materials = materials;
    }
}
