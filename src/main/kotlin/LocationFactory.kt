class LocationFactory() {
    fun create(mainFeature: Location): ILocation {
        return when (mainFeature) {
            Location.PlayGround -> PlayGround()
            Location.Pond -> Pond()
            else -> throw Exception("I don't know how to deal with ${this}")
        }
    }
}



// https://chercher.tech/kotlin/factory-design-pattern-kotlin