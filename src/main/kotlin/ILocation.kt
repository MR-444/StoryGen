interface ILocation {
    var name: String
    var description: String?
    val containingObjects: Set<RealWorldObject>?
}
