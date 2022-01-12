package domain.location

import domain.realWorldObject.RealWorldObject

class PlayGround(
    override var name: String = "PlayGround",
    override var description: String? = "A small playground.",
    override val containingObjects: Set<RealWorldObject>? = null
) : ILocation