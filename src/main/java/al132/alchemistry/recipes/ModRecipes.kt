package al132.alchemistry.recipes

import al132.alchemistry.chemistry.ElementRegistry
import al132.alchemistry.items.ModItems
import al132.alib.utils.extensions.toImmutable
import al132.alib.utils.extensions.toStack
import net.minecraftforge.oredict.OreDictionary

/**
 * Created by al132 on 1/16/2017.
 */

val heathenSpelling = "aluminium"
val heathenSpelling2 = "caesium"

data class DissolverOreData(val prefix: String, val quantity: Int, val strs: List<String>) {
    fun toDictName(index: Int) = prefix + strs[index].first().toUpperCase() + strs[index].substring(1)
    val size = strs.size
}


object ModRecipes {
    val metals: List<String> = mutableListOf(heathenSpelling, heathenSpelling2)
            .apply { addAll(ElementRegistry.getAllElements().map { it.value.name }) }.toImmutable()

    val metalOreData: List<DissolverOreData> = listOf(
            DissolverOreData("ingot", 16, metals),
            DissolverOreData("ore", 32, metals),
            DissolverOreData("dust", 16, metals),
            DissolverOreData("block", 144, metals),
            DissolverOreData("nugget", 1, metals),
            DissolverOreData("plate", 16, metals))

    fun init() {

    }

    fun initOredict() {
        for(element in ElementRegistry.getAllElements()) {
            if(element.value.materials.contains("ingot")) {
                OreDictionary.registerOre("ingot${element.value.name.capitalize()}",ModItems.elementIngot.toStack(meta = element.key))
            }
        }
    }
}