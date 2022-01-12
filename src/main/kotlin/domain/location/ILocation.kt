package domain.location

import domain.realWorldObject.RealWorldObject

interface ILocation {
    var name: String
    var description: String?
    val containingObjects: Set<RealWorldObject>?
}
