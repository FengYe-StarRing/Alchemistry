package al132.alchemistry.fluids;

import al132.alchemistry.Reference;
import al132.alchemistry.chemistry.*;
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
        ResourceLocation still = new ResourceLocation(Reference.MODID + ":fluids/fluid_still");
        ResourceLocation flow = new ResourceLocation(Reference.MODID + ":fluids/fluid_flow");
        for(ChemicalElement element : ElementRegistry.getAllElements().values()) {
            if(element.materials.contains("fluid")) {
                fluids.add(new Fluid(element.name,still,flow,element.color));
            }
        }
        for(ChemicalCompound compound : CompoundRegistry.getAllCompounds().values()) {
            if(compound.materials.contains("fluid")) {
                fluids.add(new Fluid(compound.name,still,flow,compound.color));
            }
        }
        for(ChemicalMixture mixture : MixtureRegistry.getMixtures().values()) {
            if(mixture.materials.contains("fluid")) {
                fluids.add(new Fluid(mixture.name,still,flow,mixture.color));
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
        ModelResourceLocation resource = new ModelResourceLocation(Reference.MODID + ":fluid","fluid");
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> resource);
        ModelLoader.setCustomStateMapper(blockFluid,new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return resource;
            }
        });
    }
}
