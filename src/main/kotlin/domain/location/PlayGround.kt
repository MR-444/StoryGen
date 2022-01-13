package domain.location

import domain.realWorldObject.IRealWorldObject

class PlayGround(
    override var name: String = "PlayGround",
    override var description: String? = "A small playground.",
    override val containingObjects: Set<IRealWorldObject>? = null,
) : ILocation