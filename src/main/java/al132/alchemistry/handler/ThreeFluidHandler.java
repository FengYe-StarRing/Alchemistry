package al132.alchemistry.handler;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;

public class ThreeFluidHandler implements IFluidHandler {
    private final FluidTank inputTank1;
    private final FluidTank inputTank2;
    private final FluidTank outputTank;

    public ThreeFluidHandler(FluidTank inputTank1,FluidTank inputTank2,FluidTank outputTank) {
        this.inputTank1 = inputTank1;
        this.inputTank2 = inputTank2;
        this.outputTank = outputTank;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource,boolean doFill) {
        if(inputTank2.getFluid() != null && inputTank2.getFluid().isFluidEqual(resource)) return inputTank2.fill(resource,doFill);
        if(inputTank1.getFluid() == null || inputTank1.getFluid().isFluidEqual(resource)) {
            return inputTank1.fill(resource,doFill);
        }
        return inputTank2.fill(resource,doFill);
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
