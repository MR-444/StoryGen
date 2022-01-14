package domain.location

import domain.realWorldObject.IRealWorldObject
import domain.realWorldObject.LargeBox
import domain.realWorldObject.SandBox
import domain.realWorldObject.SmallBox

class PlayGround(
    override var name: String = "PlayGround",
    override var description: String? = "A small playground.",
    override val containingObjects: Set<IRealWorldObject>? = setOf(SandBox(), SmallBox(), LargeBox()),
) : ILocation