package al132.alchemistry.handler;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;

public class OnlyInputFluidHandler implements IFluidHandler {
    private final FluidTank tank;

    public OnlyInputFluidHandler(FluidTank tank) {
        this.tank = tank;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource,boolean doFill) {
        return tank.fill(resource,doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource,boolean doDrain) {
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain,boolean doDrain) {
        return null;
    }
}
