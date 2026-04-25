package al132.alchemistry.inventory.gui;

import al132.alchemistry.inventory.container.*;
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
    public static final int GAS_TURBINE = 4;
    public static final int DISTILLATION_CHAMBER = 5;
    public static final int REFINERY_CHAMBER = 6;
    public static final int BATTERY_BOX = 7;
    public static final int FUEL_CELL = 8;
    public static final int SOLID_FUEL_CELL = 9;

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
            case GAS_TURBINE:
                return new ContainerGasTurbine(player,tileEntity);
            case DISTILLATION_CHAMBER:
                return new ContainerDistillationChamber(player,tileEntity);
            case REFINERY_CHAMBER:
                return new ContainerRefineryChamber(player,tileEntity);
            case BATTERY_BOX:
                return new ContainerBatteryBox(player,tileEntity);
            case FUEL_CELL:
                return new ContainerFuelCell(player,tileEntity);
            case SOLID_FUEL_CELL:
                return new ContainerSolidFuelCell(player,tileEntity);
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
            case GAS_TURBINE:
                return new GuiGasTurbine(player,tileEntity);
            case DISTILLATION_CHAMBER:
                return new GuiDistillationChamber(player,tileEntity);
            case REFINERY_CHAMBER:
                return new GuiRefineryChamber(player,tileEntity);
            case BATTERY_BOX:
                return new GuiBatteryBox(player,tileEntity);
            case FUEL_CELL:
                return new GuiFuelCell(player,tileEntity);
            case SOLID_FUEL_CELL:
                return new GuiSolidFuelCell(player,tileEntity);
        }
        return null;
    }
}
