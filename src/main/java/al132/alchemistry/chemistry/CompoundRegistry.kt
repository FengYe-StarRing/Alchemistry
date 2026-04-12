package al132.alchemistry.chemistry

import java.awt.Color

/**
 * Created by al132 on 1/22/2017.
 */
object CompoundRegistry {
    private val compounds = HashMap<Int, ChemicalCompound>()
    var internalChemicalIndex = 0

    fun init() {
        addInternal(Compound {
            name = "water"
            color = Color(17, 94, 192)
            components = listOf(
                    CompoundPair("hydrogen", 2),
                    CompoundPair("oxygen", 1))
        })
    }

    private fun addInternal(compound: ChemicalCompound) {
        this.compounds[internalChemicalIndex] = compound
        internalChemicalIndex++
    }

    fun addExternal(meta: Int, _name: String, _color: Color, _components: List<CompoundPair>) {
        val temp = Compound {
            name = _name
            color = _color
            components = _components
            isInternalCompound = false
        }
        if (this.compounds[meta] == null) this.compounds[meta] = temp
    }

    operator fun get(name: String) = compounds.values.firstOrNull { it.name == name }

    operator fun get(index: Int): ChemicalCompound? = compounds[index]

    fun getMeta(name: String): Int = (compounds.entries.firstOrNull { it.value.name == name }?.key) ?: -1

    fun compounds(): Collection<ChemicalCompound> = this.compounds.values

    fun keys(): Collection<Int> = this.compounds.keys
}