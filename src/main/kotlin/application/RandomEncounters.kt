package application

//  This class manages the random encounter
//  - random encounter with monsters (e.g. a dog)
//  - random encounter with real world objects  (e.g. a tree)


// interaction with encounters in both directions.
// interaction with the environment

import domain.livingThing.Dog
import domain.livingThing.Monster
import domain.location.Location
import domain.location.LocationFactory
import domain.realWorldObject.IRealWorldObject
import domain.realWorldObject.RealWorldObject
import kotlin.random.Random

// Define an interface for an Encounter
interface Encounter {
    val name: String
    fun interact(): String
}

// Concrete implementation for Monster encounter
class MonsterEncounter(private val monster: Monster) : Encounter {
    override val name: String
        get() = monster.name

    override fun interact() = "You encounter a wild $name. Prepare for battle!"
}

// Concrete implementation for RealWorldObject encounter
class RealWorldObjectEncounter(private val realWorldObject: IRealWorldObject) : Encounter {
    override val name: String
        get() = realWorldObject.javaClass.simpleName

    override fun interact() = "You see a $name. It might be useful..."
}

// This class manages the random encounters
class RandomEncounters {
    private val locationFactory = LocationFactory()

    // Randomly generate an encounter
    fun generateEncounter(): Encounter {
        val encounterType = Random.nextInt(0, 2)
        return when (encounterType) {
            0 -> MonsterEncounter(Dog("Fido", 100, locationFactory.create(Location.PlayGround), 21.0))
            else -> RealWorldObjectEncounter(RealWorldObject("Tree", "A tall tree", 10, 10, 10, 10))
        }
    }
}

