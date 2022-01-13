package domain.location

class LocationFactory() {
    fun create(location: Location): ILocation {
        return when (location) {
            Location.PlayGround -> PlayGround()
            Location.Pond -> Pond()
        }
    }
}

// see also: https://dev.to/jimfilippou/using-the-factory-design-pattern-with-kotlin-5bh5