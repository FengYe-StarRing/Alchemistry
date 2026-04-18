package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.ContainerFluidFuelBoiler;
import al132.alchemistry.inventory.container.ContainerSolidFuelFiredBoiler;
import al132.alchemistry.inventory.container.ContainerSteamTurbine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jetbrains.annotations.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int SOLID_FUEL_FIRED_BOILER = 1;
    public static final int STEAM_TURBINE = 2;
    public static final int FLUID_FUEL_BOILER = 3;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID,EntityPlayer player,World world,int x,int y,int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        switch (ID) {
            case SOLID_FUEL_FIRED_BOILER:
                return new ContainerSolidFuelFiredBoiler(player,tileEntity);
            case STEAM_TURBINE:
                return new ContainerSteamTurbine(player,tileEntity);
            case FLUID_FUEL_BOILER:
                return new ContainerFluidFuelBoiler(player,tileEntity);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID,EntityPlayer player,World world,int x,int y,int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        switch (ID) {
            case SOLID_FUEL_FIRED_BOILER:
                return new GuiSolidFuelFiredBoiler(player,tileEntity);
            case STEAM_TURBINE:
                return new GuiSteamTurbine(player,tileEntity);
            case FLUID_FUEL_BOILER:
                return new GuiFluidFuelBoiler(player,tileEntity);
        }
        return null;
    }
}
