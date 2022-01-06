abstract class Monster (open val name: String,
                        open var health: Int,
                        open var location: ILocation): LandDweller{

    abstract val locationHistory : LocationStack

    fun healthToLiteral() = when (health){
            100 -> Health.Healthy.text
            in 75..99 -> Health.Injured.text
            in 50..74 -> Health.HeavilyInjured.text
            in 25..49 -> Health.Crippled.text
            in 1..24 -> Health.NearlyDead.text
            else -> Health.Dead.text
    }

    // move actions
    abstract fun moveToAndBack(destination: ILocation)

    /**
     * Jump over an object and the move to a location.
     *
     */
    abstract fun jumpOver(location: ILocation, realWorldObject: RealWorldObject)
}