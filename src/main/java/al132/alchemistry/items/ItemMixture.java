package al132.alchemistry.items;

import al132.alchemistry.chemistry.ChemicalMixture;
import al132.alchemistry.chemistry.MixtureRegistry;
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

public class ItemMixture extends ItemMetaBase {
    public ItemMixture(String name) {
        super(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for(Map.Entry<Integer,ChemicalMixture> mixture : MixtureRegistry.getMixtures().entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this,mixture.getKey(),new ModelResourceLocation(getRegistryName().toString(),"inventory"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items) {
        if(!isInCreativeTab(tab)) {
            return;
        }
        for(Map.Entry<Integer,ChemicalMixture> mixture : MixtureRegistry.get(new String[]{"default"})) {
            items.add(new ItemStack(this,1,mixture.getKey()));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("item.mixture_" + MixtureRegistry.get(stack.getMetadata()).name + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack,World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
        tooltip.add(MixtureRegistry.get(stack.getMetadata()).toAbbreviatedString());
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return MixtureRegistry.get(itemStack.getMetadata()).getBurnTime();
    }
}
