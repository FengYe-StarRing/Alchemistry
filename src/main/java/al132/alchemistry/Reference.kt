package al132.alchemistry

import al132.alchemistry.items.ModItems
import al132.alib.utils.extensions.toStack
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import java.io.File
import java.text.DecimalFormat

object Reference {
    const val MODID = "alchemistry"
    const val MODNAME = "Alchemistry"
    const val VERSION = "1.12.2-1.0.0"
    const val DEPENDENCIES = "required-after:forgelin;required-after:alib;required-after:modularmachinery;after:crafttweaker;"
    val DECIMAL_FORMAT = DecimalFormat("#0.00")

    val pathPrefix = "alchemistry:"
    lateinit var configPath: String
    lateinit var configDir: File

    val creativeTab: CreativeTabs = object : CreativeTabs("alchemistry") {
        override fun createIcon(): ItemStack = ModItems.elementIngot.toStack(1)
    }
}