package domain.location

import domain.RealWorldObject.RealWorldObject

class Pond(
    override var name: String = "Pond",
    override var description: String? = "A lovely small pond.",
    override val containingObjects: Set<RealWorldObject>? = null
) : ILocation {
    // print the description of all objects in this location.
    fun describeObjects() {
        containingObjects?.forEach { println(it) }
    }
}