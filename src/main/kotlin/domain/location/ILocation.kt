package domain.location

import domain.RealWorldObject.RealWorldObject

interface ILocation {
    var name: String
    var description: String?
    val containingObjects: Set<RealWorldObject>?
}
