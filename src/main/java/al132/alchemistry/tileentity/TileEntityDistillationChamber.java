package al132.alchemistry.tileentity;

import al132.alchemistry.energy.AlchemistryEnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDistillationChamber extends TileEntityMachine {
    public TileEntityDistillationChamber() {
        super(new ItemStackHandler(3),new ItemStackHandler(3),new FluidTank[]{new FluidTank(1000),new FluidTank(1000)},new AlchemistryEnergyStorage(1000));
    }

    @Override
    public void update() {
        if(world.isRemote) {
            return;
        }
        markDirty();
    }
}
