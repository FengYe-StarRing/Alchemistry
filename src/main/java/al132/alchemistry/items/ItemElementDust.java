package al132.alchemistry.items;

import al132.alchemistry.chemistry.ChemicalElement;
import al132.alchemistry.chemistry.ElementRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;

public class ItemElementDust extends ItemMetaBase {
    public ItemElementDust(String name) {
        super(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for(Map.Entry<Integer, ChemicalElement> element : ElementRegistry.get(new String[]{"dust"})) {
            ModelLoader.setCustomModelResourceLocation(this,element.getKey(),new ModelResourceLocation(getRegistryName().toString(),"inventory"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items) {
        if(!isInCreativeTab(tab)) {
            return;
        }
        for(Map.Entry<Integer,ChemicalElement> element : ElementRegistry.get(new String[]{"dust"})) {
            items.add(new ItemStack(this,1,element.getKey()));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        int meta = stack.getMetadata();
        if(!ElementRegistry.keys().contains(meta)) {
            meta = 1;
        }
        return I18n.format("item.element_dust_" + ElementRegistry.get(meta).name + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack,World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
        tooltip.add(ElementRegistry.get(stack.getItemDamage()).toAbbreviatedString());
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return ElementRegistry.get(itemStack.getMetadata()).burnTime;
    }
}
