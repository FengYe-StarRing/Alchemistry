package al132.alchemistry.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyUtil {
    /**
     * 将源方块实体中的能量转移到目标方块实体中
     * @param tile 源方块实体
     * @param targetTile 目标方块实体
     * @param facing 源方块的交互面
     * @return 转移成功的能量数量
     * @param targetFacing 目标方块的交互面
     */
    public static int transfer(TileEntity tile,TileEntity targetTile,EnumFacing facing,EnumFacing targetFacing,boolean simulate) {
        IEnergyStorage storage1 = tile.getCapability(CapabilityEnergy.ENERGY,facing);
        IEnergyStorage storage2 = targetTile.getCapability(CapabilityEnergy.ENERGY,targetFacing);
        if(storage1 != null && storage2 != null) {
            int maxTransfer = Math.min(storage2.receiveEnergy(Integer.MAX_VALUE,true),storage1.extractEnergy(Integer.MAX_VALUE,true));
            return storage2.receiveEnergy(storage1.extractEnergy(maxTransfer,simulate),simulate);
        }
        return 0;
    }

    /**
     * 将储能系统中的能量转移到目标方块实体中
     * @param storage 储能系统
     * @param tile 目标方块实体
     * @param facing 目标方块的交互面
     * @param simulate 为true时仅进行数值模拟操作
     * @return 转移成功的能量数量
     */
    public static int transfer(EnergyStorage storage,TileEntity tile,EnumFacing facing,boolean simulate) {
        IEnergyStorage storage2 = tile.getCapability(CapabilityEnergy.ENERGY,facing);
        if(storage != null && storage2 != null) {
            int maxTransfer = Math.min(storage2.receiveEnergy(Integer.MAX_VALUE,true),storage.extractEnergy(Integer.MAX_VALUE,true));
            return storage2.receiveEnergy(storage.extractEnergy(maxTransfer,simulate),simulate);
        }
        return 0;
    }
}
