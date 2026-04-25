package al132.alchemistry.handler;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class OnlyInputMultiFluidHandler implements IFluidHandler {
    private final List<FluidTank> tanks;

    public OnlyInputMultiFluidHandler(FluidTank[] tanks) {
        this.tanks = Arrays.asList(tanks);
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource,boolean doFill) {
        for(FluidTank tank : tanks) {
            if(tank.getFluid() != null && tank.getFluid().isFluidEqual(resource)) {
                return tank.fill(resource,doFill);
            }
        }
        for(FluidTank tank : tanks) {
            if(tank.getFluid() == null) {
                return tank.fill(resource,doFill);
            }
        }

        return 0;
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
