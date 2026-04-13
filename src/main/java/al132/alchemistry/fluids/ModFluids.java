package al132.alchemistry.fluids;

import al132.alchemistry.chemistry.ChemicalCompound;
import al132.alchemistry.chemistry.ChemicalElement;
import al132.alchemistry.chemistry.CompoundRegistry;
import al132.alchemistry.chemistry.ElementRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModFluids {
    private static final List<Fluid> fluids = new ArrayList<>();

    public static void registerFluids() {
        ResourceLocation still = new ResourceLocation("alchemistry:fluids/fluid_still");
        ResourceLocation flow = new ResourceLocation("alchemistry:fluids/fluid_flow");
        for(ChemicalElement element : ElementRegistry.getAllElements().values()) {
            if(element.getMaterials().contains("fluid")) {
                fluids.add(new Fluid(element.getName(),still,flow,element.getColor()));
            }
        }
        for(ChemicalCompound compound : CompoundRegistry.getAllCompounds().values()) {
            if(compound.getMaterials().contains("fluid")) {
                fluids.add(new Fluid(compound.getName(),still,flow,compound.getColor()));
            }
        }
        for(Fluid fluid : fluids) {
            FluidRegistry.registerFluid(fluid);
            fluid.setBlock(new BlockFluidClassic(fluid,Material.WATER).setRegistryName(fluid.getName()));
        }
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(Fluid fluid : fluids) {
            event.getRegistry().register(fluid.getBlock());
        }
    }

    public static void registerModels() {
        for(Fluid fluid : fluids) {
            registerFluidModel(fluid.getBlock());
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerFluidModel(Block blockFluid) {
        Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelResourceLocation resource = new ModelResourceLocation("alchemistry:fluid","fluid");
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> resource);
        ModelLoader.setCustomStateMapper(blockFluid,new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return resource;
            }
        });
    }
}
