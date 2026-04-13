package al132.alchemistry

import al132.alchemistry.chemistry.CompoundRegistry
import al132.alchemistry.chemistry.ElementRegistry
import al132.alchemistry.recipes.ModRecipes
import crafttweaker.CraftTweakerAPI
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

open class CommonProxy {
    open fun preInit(e: FMLPreInitializationEvent) {
        Alchemistry.logger = e.modLog
        ElementRegistry.init()
        CompoundRegistry.init()

        if (Loader.isModLoaded("crafttweaker")) CraftTweakerAPI.tweaker.loadScript(false, "alchemistry")
    }

    open fun init(e: FMLInitializationEvent) {

    }

    open fun postInit(e: FMLPostInitializationEvent) {
        ModRecipes.init()
    }
}