package domain.livingThing

import domain.location.ILocation
import domain.realWorldObject.IRealWorldObject
import java.util.*

abstract class Monster(
    open val name: String,
    open var health: Int,
    open var location: ILocation
) : LandDweller {

    abstract val locationHistory: LinkedList<ILocation>

    fun healthToLiteral() = when (health) {
        100 -> Health.Healthy.text
        in 75..99 -> Health.Injured.text
        in 50..74 -> Health.HeavilyInjured.text
        in 25..49 -> Health.Crippled.text
        in 1..24 -> Health.NearlyDead.text
        else -> Health.Dead.text
    }

    // move actions
    abstract fun moveToAndBack(destination: ILocation, realWorldObject: IRealWorldObject?)

    /**
     * Jump over an object
     *
     */
    abstract fun jumpOver(realWorldObject: IRealWorldObject): Boolean

    /**
     * Follow the complete locations (in locationHistory)
     */
    abstract fun backTrack()
}