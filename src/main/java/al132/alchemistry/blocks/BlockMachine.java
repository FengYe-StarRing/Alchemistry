package al132.alchemistry.blocks;

import al132.alchemistry.Alchemistry;
import al132.alchemistry.Reference;
import al132.alchemistry.tileentity.TileEntityMachine;
import al132.alib.blocks.ALTileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

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
            if(inputItemHandler != null && outputItemHandler != null) {
                for(int slot = 0;slot < inputItemHandler.getSlots();slot++) {
                    Block.spawnAsEntity(worldIn,pos,inputItemHandler.getStackInSlot(slot));
                    inputItemHandler.setStackInSlot(slot,ItemStack.EMPTY);
                }
                for(int slot = 0;slot < outputItemHandler.getSlots();slot++) {
                    Block.spawnAsEntity(worldIn,pos,outputItemHandler.getStackInSlot(slot));
                    outputItemHandler.setStackInSlot(slot,ItemStack.EMPTY);
                }
                worldIn.updateComparatorOutputLevel(pos,this);
            }
        }
        super.breakBlock(worldIn,pos,state);
    }
}
