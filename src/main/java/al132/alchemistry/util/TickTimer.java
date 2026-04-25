package al132.alchemistry.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class TickTimer implements INBTSerializable<NBTTagCompound> {
    public int tick;
    public int lastTick;

    public void reset() {
        lastTick = tick;
        tick = 0;
    }

    public boolean hasTimePassed(int maxTick) {
        if(++tick >= maxTick) {
            reset();
            return true;
        }
        return false;
    }

    public void undoReset() {
        tick = lastTick;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Tick",tick);
        nbt.setInteger("LastTick",lastTick);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        tick = nbt.getInteger("Tick");
        lastTick = nbt.getInteger("LastTick");
    }
}
