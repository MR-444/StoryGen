class LocationFactory (mainFeature: String) : ILocation {

    override var name = mainFeature

    fun create(name: String): ILocation {
        return when (name) {
            "PlayGround" -> PlayGround(name)
            "Pond" -> Pond(name)
            else -> throw Exception("I don't know how to deal with $name.")
        }
    }
}



// https://chercher.tech/kotlin/factory-design-pattern-kotlin