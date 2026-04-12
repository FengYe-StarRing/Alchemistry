package al132.alchemistry

import al132.alchemistry.capability.CapabilityDrugInfo
import al132.alchemistry.chemistry.CompoundRegistry
import al132.alchemistry.items.ItemCompound
import al132.alib.utils.extensions.translate
import net.minecraft.item.ItemFood
import net.minecraftforge.client.event.FOVUpdateEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ClientEventHandler {

    @SubscribeEvent
    fun fovEvent(e: FOVUpdateEvent) {
        e.entity.getCapability(CapabilityDrugInfo.DRUG_INFO, null)?.let { info ->
            if (info.psilocybinTicks > 500) {
                e.newfov = info.cumulativeFOVModifier// + e.fov
                info.cumulativeFOVModifier -= .002f
                info.psilocybinTicks--
            } else {
                info.cumulativeFOVModifier = 1.0f
            }
        }
    }
}