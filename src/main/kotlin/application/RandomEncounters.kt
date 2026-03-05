package application

//  This class manages the random encounter
//  - random encounter with characters
//  - random encounter with real world objects

import domain.livingThing.Character
import domain.realWorldObject.WorldObject

// Define an interface for an Encounter
interface Encounter {
    val name: String
    fun interact(): String
}

// Concrete implementation for Character encounter
class CharacterEncounter(private val character: Character) : Encounter {
    override val name: String
        get() = character.name

    override fun interact() = "You encounter ${character.name} the ${character.type}. ${character.description}"
}

// Concrete implementation for RealWorldObject encounter
class RealWorldObjectEncounter(private val worldObject: WorldObject) : Encounter {
    override val name: String
        get() = worldObject.name

    override fun interact() = "You see a $name. It might be useful..."
}

// This class manages the random encounters using a Registry pattern
class RandomEncounters {
    private val templates = mutableListOf<() -> Encounter>()

    /**
     * Register an encounter template (a lambda that creates the encounter).
     * This allows adding new encounters at runtime without modifying this class.
     */
    fun register(template: () -> Encounter) {
        templates.add(template)
    }

    /**
     * Randomly generate an encounter from the registered templates.
     */
    fun generateEncounter(): Encounter? {
        if (templates.isEmpty()) return null
        return templates.random().invoke()
    }
}
