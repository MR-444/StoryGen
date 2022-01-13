package domain.location

import domain.realWorldObject.IRealWorldObject

interface ILocation {
    var name: String
    var description: String?
    val containingObjects: Set<IRealWorldObject>?
}
