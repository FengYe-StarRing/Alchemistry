package al132.alchemistry.items;

import al132.alchemistry.chemistry.CompoundRegistry;
import al132.alchemistry.chemistry.ElementRegistry;
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
        } else if(item instanceof ItemElementIngot && ElementRegistry.INSTANCE.keys().contains(meta)) {
            return ElementRegistry.INSTANCE.get(meta).getColor().getRGB();
        } else if(item instanceof ItemCompound && CompoundRegistry.INSTANCE.keys().contains(meta)) {
            return CompoundRegistry.INSTANCE.get(meta).getColor().getRGB();
        }
        return Color.black.getRGB();
    }
}
