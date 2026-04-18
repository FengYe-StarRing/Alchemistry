package al132.alchemistry.handler;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;

public class DualFluidHandler implements IFluidHandler {
    private final FluidTank inputTank;
    private final FluidTank outputTank;

    public DualFluidHandler(FluidTank inputTank,FluidTank outputTank) {
        this.inputTank = inputTank;
        this.outputTank = outputTank;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource,boolean doFill) {
        return inputTank.fill(resource,doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource,boolean doDrain) {
        return outputTank.drain(resource,doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain,boolean doDrain) {
        return outputTank.drain(maxDrain,doDrain);
    }
}
