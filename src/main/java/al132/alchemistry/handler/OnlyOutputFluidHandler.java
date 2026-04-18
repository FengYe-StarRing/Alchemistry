package al132.alchemistry.handler;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;

public class OnlyOutputFluidHandler implements IFluidHandler {
    private final FluidTank tank;

    public OnlyOutputFluidHandler(FluidTank tank) {
        this.tank = tank;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource,boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource,boolean doDrain) {
        return tank.drain(resource,doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain,boolean doDrain) {
        return tank.drain(maxDrain,doDrain);
    }
}
