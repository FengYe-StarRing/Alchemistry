package al132.alchemistry.items;

import al132.alchemistry.chemistry.CompoundRegistry;
import al132.alchemistry.chemistry.ElementRegistry;
import al132.alchemistry.chemistry.MixtureRegistry;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class ItemColorHandler implements IItemColor {
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        Item item = stack.getItem();
        int meta = stack.getMetadata();

        if(tintIndex != 0) {
            return Color.white.getRGB();
        } else if(item instanceof ItemElementIngot && ElementRegistry.keys().contains(meta)) {
            return ElementRegistry.get(meta).color.getRGB();
        } else if(item instanceof ItemElementDust && ElementRegistry.keys().contains(meta)) {
            return ElementRegistry.get(meta).color.getRGB();
        } else if(item instanceof ItemCompound && CompoundRegistry.keys().contains(meta)) {
            return CompoundRegistry.get(meta).color.getRGB();
        } else if(item instanceof ItemCompoundDust && CompoundRegistry.keys().contains(meta)) {
            return CompoundRegistry.get(meta).color.getRGB();
        } else if(item instanceof ItemMixture && MixtureRegistry.getMixtures().containsKey(meta)) {
            return MixtureRegistry.get(meta).color.getRGB();
        }
        return Color.black.getRGB();
    }
}
