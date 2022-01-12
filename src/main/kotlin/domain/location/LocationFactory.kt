package domain.location

class LocationFactory() {
    fun create(mainFeature: Location): ILocation {
        return when (mainFeature) {
            Location.PlayGround -> PlayGround()
            Location.Pond -> Pond()
        }
    }
}

// see also: https://dev.to/jimfilippou/using-the-factory-design-pattern-with-kotlin-5bh5