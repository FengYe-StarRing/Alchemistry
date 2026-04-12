package al132.alchemistry.recipes

import al132.alchemistry.chemistry.CompoundRegistry
import al132.alchemistry.chemistry.ElementRegistry
import al132.alchemistry.items.ItemElementIngot
import al132.alchemistry.items.ModItems
import al132.alib.utils.Utils.oreExists
import al132.alib.utils.extensions.toImmutable
import al132.alib.utils.extensions.toStack
import net.minecraftforge.fluids.FluidRegistry
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
    val electrolyzerRecipes = ArrayList<ElectrolyzerRecipe>()
    val dissolverRecipes = ArrayList<DissolverRecipe>()
    val combinerRecipes = ArrayList<CombinerRecipe>()
    val evaporatorRecipes = ArrayList<EvaporatorRecipe>()
    val atomizerRecipes = ArrayList<AtomizerRecipe>()
    val liquifierRecipes = ArrayList<LiquifierRecipe>()
    val fissionRecipes = ArrayList<FissionRecipe>()

    val metals: List<String> = mutableListOf(heathenSpelling, heathenSpelling2)
            .apply { addAll(ElementRegistry.getAllElements().map { it.name }) }.toImmutable()

    val metalOreData: List<DissolverOreData> = listOf(
            DissolverOreData("ingot", 16, metals),
            DissolverOreData("ore", 32, metals),
            DissolverOreData("dust", 16, metals),
            DissolverOreData("block", 144, metals),
            DissolverOreData("nugget", 1, metals),
            DissolverOreData("plate", 16, metals))

    fun init() {
        initElectrolyzerRecipes()
        initEvaporatorRecipes()
        initFuelHandler()
        initDissolverRecipes() //before combiner, so combiner can use reversible recipes
        initCombinerRecipes()
        initAtomizerRecipes()
        initLiquifierRecipes()
        initFissionRecipes()
    }

    fun initOredict() {
        for(element in ElementRegistry.getAllElements()) {
            if(ItemElementIngot.invalidIngots.contains(element.meta)) {
                continue
            }
            OreDictionary.registerOre("ingot${element.name.capitalize()}",ModItems.ingots.toStack(meta = element.meta))
        }
    }
/*

    fun addDissolverRecipesForAlloy(alloySuffix: String, //Should start with uppercase, i.e. "Bronze" or "ElectricalSteel"
                                    ingotOne: String,
                                    quantityOne: Int,
                                    ingotTwo: String,
                                    quantityTwo: Int,
                                    ingotThree: String = "",
                                    quantityThree: Int = 0,
                                    conservationOfMass: Boolean = true) {
        fun fitInto16(q1: Int, q2: Int, q3: Int): List<Int>? {
            val sum = q1 + q2 + q3
            val new1 = Math.round(q1.toDouble() / sum * 16.0).toInt()
            val new2 = Math.round(q2.toDouble() / sum * 16.0).toInt()
            val new3 = Math.round(q3.toDouble() / sum * 16.0).toInt()
            if ((16).rem(sum) == 0) return listOf(new1, new2, new3)
            else return null
        }

        val sum = quantityOne + quantityTwo + quantityThree


        val ores: List<String> = listOf(("ingot$alloySuffix"), ("plate$alloySuffix"), ("dust$alloySuffix"))
        val threeIngredients: Boolean = ingotThree.isNotEmpty() && quantityThree > 0
        val fractionalQuantities = fitInto16(quantityOne, quantityTwo, quantityThree)
        val isConserved = fractionalQuantities != null && conservationOfMass
        val calculatedQuantity1 = if (isConserved) fractionalQuantities!![0] else quantityOne * 16
        val calculatedQuantity2 = if (isConserved) fractionalQuantities!![1] else quantityOne * 16
        val calculatedQuantity3 = if (isConserved) fractionalQuantities!![2] else quantityOne * 16

        ores.filter { oreNotEmpty(it) }
                .forEach { ore ->
                    dissolverRecipes.add(dissolverRecipe {
                        input = ore.toOre()
                        //if (fractionalQuantities == null && conservationOfMass) inputQuantity = quantityOne + quantityTwo + quantityThree
                        output {
                            addGroup {
                                addStack { ingotOne.toStack(quantity = calculatedQuantity1) }
                                addStack { ingotTwo.toStack(quantity = calculatedQuantity2) }
                                if (threeIngredients) {
                                    addStack { ingotThree.toStack(quantity = calculatedQuantity3) }
                                }
                            }
                        }
                    })
                }

        if (oreNotEmpty("block$alloySuffix")) {
            dissolverRecipes.add(dissolverRecipe {
                input = ("block$alloySuffix").toOre()
                output {
                    addGroup {
                        if (threeIngredients) {
                            addStack { ingotOne.toStack(calculatedQuantity1 * 9) }//quantity = 144 / (quantityOne + quantityTwo + quantityThree) * quantityOne) }
                            addStack { ingotTwo.toStack(calculatedQuantity2 * 9) }//quantity = 144 / (quantityOne + quantityTwo + quantityThree) * quantityTwo) }
                            addStack { ingotThree.toStack(calculatedQuantity3 * 9) }//quantity = 144 / (quantityOne + quantityTwo + quantityThree) * quantityThree) }
                        } else {
                            addStack { ingotOne.toStack(quantity = calculatedQuantity1 * 9) }//144 / (quantityOne + quantityTwo) * quantityOne) }
                            addStack { ingotTwo.toStack(quantity = calculatedQuantity2 * 9) }//144 / (quantityOne + quantityTwo) * quantityTwo) }
                        }
                    }
                }
            })
        }
    }*/


    fun initDissolverRecipes() {

    }


    fun initElectrolyzerRecipes() {

    }


    fun initEvaporatorRecipes() {

    }


    fun initFuelHandler() {

    }


    fun initCombinerRecipes() {

    }

    fun initAtomizerRecipes() {

    }

    fun initLiquifierRecipes() {

    }

    fun initFissionRecipes() {
        for (i in ElementRegistry.keys().filterNot { it == 1 }) {
            val output1 = if (i % 2 == 0) i / 2 else (i / 2) + 1
            val output2 = if (i % 2 == 0) 0 else i / 2
            if (ElementRegistry[output1] != null && (output2 == 0 || ElementRegistry[output2] != null)) {
                fissionRecipes.add(FissionRecipe(i, output1, output2))
            }
        }
    }
}

fun fluidExists(name: String): Boolean = FluidRegistry.isFluidRegistered(name)

fun oreNotEmpty(ore: String) = oreExists(ore) && OreDictionary.getOres(ore).isNotEmpty()