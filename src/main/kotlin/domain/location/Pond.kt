package domain.location

import domain.realWorldObject.IRealWorldObject
import domain.realWorldObject.WaterLilies

class Pond(
    override var name: String = "Pond",
    override var description: String? = "A lovely small pond.",
    override val containingObjects: Set<IRealWorldObject>? = setOf(WaterLilies()),
) : ILocation {
    // print the description of all objects in this location.
    fun describeObjects() {
        containingObjects?.forEach { println(it) }
    }
}