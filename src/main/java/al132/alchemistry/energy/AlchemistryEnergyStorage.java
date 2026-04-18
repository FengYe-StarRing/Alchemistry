package al132.alchemistry.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class AlchemistryEnergyStorage extends EnergyStorage implements IEnergyStorage,INBTSerializable<NBTTagCompound> {
    private int energy,maxEnergy,maxExtract,maxReceive;

    public AlchemistryEnergyStorage(int maxEnergy) {
        super(maxEnergy);
        this.energy = 0;
        this.maxEnergy = maxEnergy;
        this.maxReceive = maxEnergy;
        this.maxExtract = maxEnergy;
    }

    public AlchemistryEnergyStorage(int energy,int maxEnergy) {
        super(energy,maxEnergy);
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.maxReceive = maxEnergy;
        this.maxExtract = maxEnergy;
    }

    public AlchemistryEnergyStorage(int maxEnergy,int maxReceive,int maxExtract) {
        super(maxEnergy,maxReceive,maxExtract);
        this.energy = 0;
        this.maxEnergy = maxEnergy;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public AlchemistryEnergyStorage(int energy,int maxEnergy,int maxReceive,int maxExtract) {
        super(maxEnergy,maxReceive,maxExtract,energy);
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public int receiveEnergy(int maxReceive,boolean simulate) {
        if(!canReceive()) {
            return 0;
        }
        int receiveEnergy = Math.min(Math.min(maxReceive,maxEnergy - energy),this.maxReceive);
        if(!simulate) {
            energy += receiveEnergy;
        }
        return receiveEnergy;
    }

    @Override
    public int extractEnergy(int maxExtract,boolean simulate) {
        if(!canExtract()) {
            return 0;
        }
        int extractEnergy = Math.min(Math.min(maxExtract,energy),this.maxExtract);
        if(!simulate) {
            energy -= extractEnergy;
        }
        return extractEnergy;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxEnergy;
    }

    @Override
    public boolean canExtract() {
        return maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return maxReceive > 0;
    }

    public int getMaxReceive() {
        return maxReceive;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Energy",energy);
        nbt.setInteger("MaxEnergy",maxEnergy);
        nbt.setInteger("MaxReceive",maxReceive);
        nbt.setInteger("MaxExtract",maxExtract);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        energy = nbt.getInteger("Energy");
        maxEnergy = nbt.getInteger("MaxEnergy");
        maxReceive = nbt.getInteger("MaxReceive");
        maxExtract = nbt.getInteger("MaxExtract");
    }
}
