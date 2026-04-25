package al132.alchemistry.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class SeriesEnergyStorageHandler implements IEnergyStorage {
    public final List<IEnergyStorage> storages;

    public SeriesEnergyStorageHandler(ItemStackHandler itemStackHandler) {
        List<IEnergyStorage> storages = new ArrayList<>();
        for(int slot = 0;slot < itemStackHandler.getSlots();slot++) {
            ItemStack stack = itemStackHandler.getStackInSlot(slot);
            if(stack.hasCapability(CapabilityEnergy.ENERGY,null)) {
                IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY,null);
                if(storage != null) {
                    storages.add(storage);
                }
            }
        }
        this.storages = storages;
    }

    @Override
    public int receiveEnergy(int maxReceive,boolean simulate) {
        int receive = 0;
        for(IEnergyStorage storage : storages) {
            receive += storage.receiveEnergy(maxReceive,simulate);
            maxReceive -= receive;
        }
        return receive;
    }

    @Override
    public int extractEnergy(int maxExtract,boolean simulate) {
        int extract = 0;
        for(IEnergyStorage storage : storages) {
            extract += storage.extractEnergy(maxExtract,simulate);
            maxExtract -= extract;
        }
        return extract;
    }

    @Override
    public int getEnergyStored() {
        int energy = 0;
        for(IEnergyStorage storage : storages) {
            energy += storage.getEnergyStored();
        }
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        int maxEnergy = 0;
        for(IEnergyStorage storage : storages) {
            maxEnergy += storage.getMaxEnergyStored();
        }
        return maxEnergy;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
