package al132.alchemistry.blocks;

import al132.alchemistry.Alchemistry;
import al132.alchemistry.Reference;
import al132.alchemistry.tileentity.TileEntityMachine;
import al132.alib.blocks.ALTileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Random;

public class BlockMachine extends ALTileBlock {
    public BlockMachine(String name,Class<? extends TileEntity> tile,int guiID,int hardness) {
        super(name,Reference.INSTANCE.getCreativeTab(),tile,Alchemistry.INSTANCE,guiID,Material.ROCK,hardness);
    }

    @Override
    public void breakBlock(World worldIn,BlockPos pos,IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile instanceof TileEntityMachine) {
            ItemStackHandler inputItemHandler = ((TileEntityMachine)tile).inputItemHandler;
            ItemStackHandler outputItemHandler = ((TileEntityMachine)tile).outputItemHandler;
            if(inputItemHandler != null) {
                for(int slot = 0;slot < inputItemHandler.getSlots();slot++) {
                    Block.spawnAsEntity(worldIn,pos,inputItemHandler.getStackInSlot(slot));
                    inputItemHandler.setStackInSlot(slot,ItemStack.EMPTY);
                }
            }
            if(outputItemHandler != null) {
                for(int slot = 0;slot < outputItemHandler.getSlots();slot++) {
                    Block.spawnAsEntity(worldIn,pos,outputItemHandler.getStackInSlot(slot));
                    outputItemHandler.setStackInSlot(slot,ItemStack.EMPTY);
                }
            }
            if(inputItemHandler != null || outputItemHandler != null) {
                worldIn.updateComparatorOutputLevel(pos,this);
            }
        }
        super.breakBlock(worldIn,pos,state);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops,IBlockAccess world,BlockPos pos,IBlockState state,int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        int count = quantityDropped(state,fortune,rand);
        for(int i = 0; i < count; i++) {
            Item item = this.getItemDropped(state,rand,fortune);
            if(item != Items.AIR) {
                drops.add(new ItemStack(item,1,this.damageDropped(state)));
            }
        }
    }
}
