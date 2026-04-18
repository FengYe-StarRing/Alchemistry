package al132.alchemistry.items

import al132.alchemistry.Reference
import al132.alchemistry.recipe.ModRecipes
import al132.alib.items.ALItem
import net.minecraft.client.Minecraft
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ModItems {
    val items = ArrayList<ALItem>()

    var elementIngot = ItemElementIngot("element_ingot")
    var elementDust = ItemElementDust("element_dust")
    var compounds = ItemCompound("compound")
    var compoundDust = ItemCompoundDust("compound_dust")
    var elements = elementIngot
    val periodicDankMolecule = ItemPeriodicDiagram()


    fun registerItems(event: RegistryEvent.Register<Item>) {
        items.forEach {
            it.registerItem(event)
        }
        ModRecipes.initOredict()
    }

    @SideOnly(Side.CLIENT)
    fun registerModels() = items.forEach { it.registerModel() }

    @SideOnly(Side.CLIENT)
    fun initColors() {
        val colorHandler = ItemColorHandler()
        val itemColors = Minecraft.getMinecraft().itemColors
        itemColors.registerItemColorHandler(colorHandler,compounds)
        itemColors.registerItemColorHandler(colorHandler,elementIngot)
        itemColors.registerItemColorHandler(colorHandler,compoundDust)
        itemColors.registerItemColorHandler(colorHandler,elementDust)
    }
}

open class ItemBase(name: String) : ALItem(name, Reference.creativeTab) {
    init {
        ModItems.items.add(this)
    }
}

abstract class ItemMetaBase(name: String) : ItemBase(name) {

    init {
        this.hasSubtypes = true
    }

    fun toStack(meta: Int) = ItemStack(this,1,meta)
}