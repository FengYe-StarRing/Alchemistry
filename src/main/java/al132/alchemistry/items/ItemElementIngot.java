package al132.alchemistry.items;

import al132.alchemistry.chemistry.ElementRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ItemElementIngot extends ItemMetaBase {
    public static Set<Integer> invalidIngots = new LinkedHashSet<>();

    static {
        invalidIngots.add(26);
        invalidIngots.add(79);
    }

    public ItemElementIngot(String name) {
        super(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for(Integer element : ElementRegistry.INSTANCE.keys()) {
            if(invalidIngots.contains(element)) {
                continue;
            }
            ModelLoader.setCustomModelResourceLocation(this,element,new ModelResourceLocation(getRegistryName().toString(),"inventory"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items) {
        if(!isInCreativeTab(tab)) {
            return;
        }
        for(Integer element : ElementRegistry.INSTANCE.keys()) {
            if(invalidIngots.contains(element)) {
                continue;
            }
            items.add(new ItemStack(this,1,element));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        int meta = stack.getMetadata();
        if(!ElementRegistry.INSTANCE.keys().contains(meta)) {
            meta = 1;
        }
        return I18n.translateToLocal("item.alchemistry:ingot_" + ElementRegistry.INSTANCE.get(meta).getName() + ".name").trim();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack,World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
        tooltip.add(ElementRegistry.INSTANCE.get(stack.getItemDamage()).getAbbreviation());
    }
}
