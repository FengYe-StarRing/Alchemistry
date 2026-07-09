package al132.alchemistry.items;

import al132.alchemistry.chemistry.ChemicalCompound;
import al132.alchemistry.chemistry.CompoundRegistry;
import al132.alchemistry.energy.AlchemistryEnergyStorage;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItemStorageBattery extends ItemMetaBase {
    public ItemStorageBattery(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for (Map.Entry<Integer,ChemicalCompound> material : getBatteryMaterials()) {
            ModelLoader.setCustomModelResourceLocation(this,material.getKey(),new ModelResourceLocation(getRegistryName().toString(),"inventory"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab,NonNullList<ItemStack> items) {
        if(!isInCreativeTab(tab)) {
            return;
        }
        for (Map.Entry<Integer,ChemicalCompound> material : getBatteryMaterials()) {
            items.add(new ItemStack(this,1,material.getKey()));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        for (Map.Entry<Integer,ChemicalCompound> material : getBatteryMaterials()) {
            if(material.getKey() == stack.getMetadata()) {
                return I18n.format("item.storage_battery_" + material.getValue().name + ".name");
            }
        }
        return super.getItemStackDisplayName(stack);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack,@Nullable NBTTagCompound nbt) {
        int batteryCapacity = 0;
        for (Map.Entry<Integer,ChemicalCompound> material : getBatteryMaterials()) {
            if(material.getKey() == stack.getMetadata()) {
                batteryCapacity = material.getValue().getBatteryCapacity();
            }
        }
        if(batteryCapacity == 0) return null;
        int finalBatteryCapacity = batteryCapacity;
        return new ICapabilityProvider() {
            @Override
            public boolean hasCapability(@NotNull Capability<?> capability,@Nullable EnumFacing facing) {
                if(capability == CapabilityEnergy.ENERGY) {
                    return true;
                }
                return false;
            }
            @Override
            public @Nullable <T> T getCapability(@NotNull Capability<T> capability,@Nullable EnumFacing facing) {
                if(capability == CapabilityEnergy.ENERGY) {
                    return CapabilityEnergy.ENERGY.cast(getEnergyStorage());
                }
                return null;
            }
            private IEnergyStorage getEnergyStorage() {
                if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
                AlchemistryEnergyStorage storage = new AlchemistryEnergyStorage(finalBatteryCapacity) {
                    @Override
                    public int receiveEnergy(int maxReceive,boolean simulate) {
                        int energy = super.receiveEnergy(maxReceive,simulate);
                        stack.getTagCompound().setTag("EnergyStorage",serializeNBT());
                        return energy;
                    }
                    @Override
                    public int extractEnergy(int maxExtract,boolean simulate) {
                        int energy = super.extractEnergy(maxExtract,simulate);
                        stack.getTagCompound().setTag("EnergyStorage",serializeNBT());
                        return energy;
                    }
                };
                if(stack.getTagCompound().hasKey("EnergyStorage")) {
                    storage.deserializeNBT(stack.getTagCompound().getCompoundTag("EnergyStorage"));
                }
                return storage;
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack,World worldIn,List<String> tooltip,ITooltipFlag flagIn) {
        IEnergyStorage energy = stack.getCapability(CapabilityEnergy.ENERGY,null);
        if(energy == null) return;
        int stored = energy.getEnergyStored();
        int max = energy.getMaxEnergyStored();
        int pct = stored * 100 / max;
        tooltip.add(stored + "/" + max + " FE");
        tooltip.add(pct + "%");
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        IEnergyStorage energy = stack.getCapability(CapabilityEnergy.ENERGY,null);
        if(energy == null || energy.getMaxEnergyStored() == 0) return 1;
        return 1.0 - (double)energy.getEnergyStored() / energy.getMaxEnergyStored();
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        IEnergyStorage energy = stack.getCapability(CapabilityEnergy.ENERGY,null);
        if(energy == null) return super.getRGBDurabilityForDisplay(stack);
        double pct = (double)energy.getEnergyStored() / energy.getMaxEnergyStored();
        if(pct > 0.5) return 0x00FF00;
        if(pct > 0.2) return 0xFFFF00;
        return 0xFF0000;
    }

    private Set<Map.Entry<Integer,ChemicalCompound>> getBatteryMaterials() {
        Map<Integer,ChemicalCompound> ret = new LinkedHashMap<>();
        for(Map.Entry<Integer,ChemicalCompound> compound : CompoundRegistry.get(new String[]{"dust"})) {
            int capacity = compound.getValue().getBatteryCapacity();
            if(capacity != 0) {
                ret.put(compound.getKey(),compound.getValue());
            }
        }
        return ret.entrySet();
    }
}
