package al132.alchemistry.tileentity;

import al132.alchemistry.handler.SeriesEnergyStorageHandler;
import al132.alchemistry.util.EnergyUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityBatteryBox extends TileEntityMachine {
    public TileEntityBatteryBox() {
        super(new ItemStackHandler(27));
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        // 向周围主动输出能量
        for(EnumFacing facing : EnumFacing.values()) {
            TileEntity tile = world.getTileEntity(pos.offset(facing));
            if(tile != null) {
                EnergyUtil.transfer(this,tile,null,facing.getOpposite(),false);
            }
        }
        markDirty();
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability,facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability,@Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new SeriesEnergyStorageHandler(inputItemHandler));
        }
        return super.getCapability(capability,facing);
    }
}
