package al132.alchemistry.items;

import al132.alchemistry.chemistry.CompoundRegistry;
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

import java.util.List;

public class ItemCompound extends ItemMetaBase {
    public ItemCompound(String name) {
        super(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for(Integer compound : CompoundRegistry.INSTANCE.keys()) {
            ModelLoader.setCustomModelResourceLocation(this,compound,new ModelResourceLocation(getRegistryName().toString(),"inventory"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack,World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
        tooltip.add(CompoundRegistry.INSTANCE.get(stack.getMetadata()).toAbbreviatedString());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items) {
        if(!isInCreativeTab(tab)) {
            return;
        }
        for(Integer compound : CompoundRegistry.INSTANCE.keys()) {
            items.add(new ItemStack(this,1,compound));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal("item.alchemistry:compound_" + CompoundRegistry.INSTANCE.get(stack.getMetadata()).getName() + ".name");
    }
}
