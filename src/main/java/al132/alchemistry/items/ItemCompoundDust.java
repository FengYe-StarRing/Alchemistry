package al132.alchemistry.items;

import al132.alchemistry.chemistry.ChemicalCompound;
import al132.alchemistry.chemistry.CompoundRegistry;
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

public class ItemCompoundDust extends ItemMetaBase {
    public ItemCompoundDust(String name) {
        super(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for(Map.Entry<Integer,ChemicalCompound> compound : CompoundRegistry.get(new String[]{"dust"})) {
            ModelLoader.setCustomModelResourceLocation(this,compound.getKey(),new ModelResourceLocation(getRegistryName().toString(),"inventory"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack,World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
        tooltip.add(CompoundRegistry.get(stack.getMetadata()).toAbbreviatedString());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items) {
        if(!isInCreativeTab(tab)) {
            return;
        }
        for(Map.Entry<Integer,ChemicalCompound> compound : CompoundRegistry.get(new String[]{"dust"})) {
            items.add(new ItemStack(this,1,compound.getKey()));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("item.compound_dust_" + CompoundRegistry.get(stack.getMetadata()).name + ".name");
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return CompoundRegistry.get(itemStack.getMetadata()).getBurnTime();
    }
}
