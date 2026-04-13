package al132.alchemistry.blocks

import al132.alchemistry.Reference
import al132.alib.blocks.ALBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ModBlocks {
    val blocks = ArrayList<ALBlock>()

    fun registerBlocks(event: RegistryEvent.Register<Block>) = blocks.forEach { it.registerBlock(event) }

    fun registerItemBlocks(event: RegistryEvent.Register<Item>) = blocks.forEach { it.registerItemBlock(event) }

    @SideOnly(Side.CLIENT)
    fun registerModels() = blocks.forEach { it.registerModel() }
}

open class BaseBlock(name: String, material: Material = Material.ROCK) : ALBlock(name, Reference.creativeTab, material) {
    init {
        ModBlocks.blocks.add(this)
    }
}