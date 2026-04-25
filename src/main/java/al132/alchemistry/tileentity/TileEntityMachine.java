package al132.alchemistry.tileentity;

import al132.alchemistry.energy.AlchemistryEnergyStorage;
import al132.alib.tiles.ALTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class TileEntityMachine extends ALTile implements ITickable {
    public final ItemStackHandler inputItemHandler;
    public final ItemStackHandler outputItemHandler;
    public final FluidTank[] fluidTanks;
    public final AlchemistryEnergyStorage energyStorage;

    public TileEntityMachine(ItemStackHandler inputItemHandler,ItemStackHandler outputItemHandler,FluidTank[] fluidTanks) {
        this.inputItemHandler = inputItemHandler;
        this.outputItemHandler = outputItemHandler;
        this.fluidTanks = fluidTanks;
        energyStorage = null;
    }

    public TileEntityMachine(FluidTank[] fluidTanks,AlchemistryEnergyStorage energyStorage) {
        inputItemHandler = null;
        outputItemHandler = null;
        this.fluidTanks = fluidTanks;
        this.energyStorage = energyStorage;
    }

    public TileEntityMachine(ItemStackHandler inputItemHandler,ItemStackHandler outputItemHandler,FluidTank[] fluidTanks,AlchemistryEnergyStorage energyStorage) {
        this.inputItemHandler = inputItemHandler;
        this.outputItemHandler = outputItemHandler;
        this.fluidTanks = fluidTanks;
        this.energyStorage = energyStorage;
    }

    public TileEntityMachine(ItemStackHandler itemHandler) {
        inputItemHandler = itemHandler;
        this.outputItemHandler = null;
        this.fluidTanks = null;
        this.energyStorage = null;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos,1,getUpdateTag());
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void markDirty() {
        super.markDirty();
        world.notifyBlockUpdate(pos,world.getBlockState(pos),world.getBlockState(pos),3);
    }

    @Override
    public boolean shouldRefresh(World world,BlockPos pos,IBlockState oldState,IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(inputItemHandler != null) inputItemHandler.deserializeNBT(compound.getCompoundTag("InputItemHandler"));
        if(outputItemHandler != null) outputItemHandler.deserializeNBT(compound.getCompoundTag("OutputItemHandler"));
        if(fluidTanks != null) for(int i = 0;i < fluidTanks.length;i++) {
            fluidTanks[i].readFromNBT(compound.getCompoundTag("FluidTank" + i));
        }
        if(energyStorage != null) energyStorage.deserializeNBT(compound.getCompoundTag("EnergyStorage"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(inputItemHandler != null) compound.setTag("InputItemHandler",inputItemHandler.serializeNBT());
        if(outputItemHandler != null) compound.setTag("OutputItemHandler",outputItemHandler.serializeNBT());
        if(fluidTanks != null) for(int i = 0;i < fluidTanks.length;i++) {
            compound.setTag("FluidTank" + i,fluidTanks[i].writeToNBT(new NBTTagCompound()));
        }
        if(energyStorage != null) compound.setTag("EnergyStorage",energyStorage.serializeNBT());
        return compound;
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        markDirty();
    }
}
