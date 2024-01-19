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
import domain.realWorldObject.RealWorldObjectFactory
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
class RandomEncounters(private val realWorldObjectFactory: RealWorldObjectFactory) {

    private val randomEncounters: List<Encounter> = RealWorldObject.values().map {
        RealWorldObjectEncounter(realWorldObjectFactory.create(it))
    } + listOf(
        // take a dog because there are no generic monsters yet.
        MonsterEncounter(Dog("Fido", 100, LocationFactory().create(Location.PlayGround), 21.0)
        // TODO: add more monsters
        )
    )

    private fun getRandomEncounter(): Encounter {
        val randomIndex = Random.nextInt(randomEncounters.size)
        return randomEncounters[randomIndex]
    }

    fun interactWithEncounter(encounter: Encounter) {
        println(encounter.interact())
    }

    fun interactWithEnvironment() {
        val encounter = getRandomEncounter()
        println("While exploring, " + encounter.interact())
    }
}

