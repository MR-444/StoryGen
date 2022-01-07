import java.util.LinkedList

data class Dog(
    override val name: String,
    override var health: Int,
    override var location: ILocation,
    var height: Double) : Monster(name, health, location) {

    override val locationHistory = LinkedList<ILocation>()

    val bark = "woof"

    fun say(s: String) = println (s)

    fun giveLastVisitedLocation(): ILocation = locationHistory.last

    override fun moveToAndBack(destination: ILocation, realWorldObject: RealWorldObject?) {

        println()
        println("Command: ${name} move to ${destination.name} and back.")
        say("I am starting at the ${location.name}.")

        if (realWorldObject == null ) {

            printLocationHistory()

            // just move to another location
            //remember the actual starting location
            locationHistory.add(location)

            say("I am running to the ${destination.name}.")

            location= destination
            locationHistory.add(destination)
            say("I am now at ${destination.name}.")

            printLocationHistory()

            // run back
            // add the new destination which was the start.
            say("I am running back to the ${getSuccessorLocation(this.location).name}.")
            locationHistory.add(getSuccessorLocation(this.location))
            location = getSuccessorLocation(this.location)

            printLocationHistory()

            this.say("I am at ${location.name} and have visited the ${getSuccessorLocation(this.location).name}.")
        }
        else
        {   // move to another location with an obstacle between
            println("And jump over the ${realWorldObject.name}.")
            if (jumpOver(realWorldObject)) {
                rememberDestination(destination)
            }
        }
    }

    private fun getSuccessorLocation (location: ILocation) : ILocation
    {
        println("Returned Element: " + locationHistory[locationHistory.lastIndexOf(location)-1])
        return locationHistory[locationHistory.lastIndexOf(location)-1]
    }

    private fun rememberDestination(destination: ILocation) {
        //remember the destination location now the old one...
        say("I am running back to the ${locationHistory[locationHistory.size - 1].name}.")
        locationHistory.add(destination)
    }

    override fun jumpOver(realWorldObject: RealWorldObject) :Boolean {
        val jumpable = this.canJumpOver(realWorldObject)
        if (jumpable) {
            this.say("Jumping over ${realWorldObject.name} ... whee. :)")
        }
        else {
            this.say("I am to small to jump over ${realWorldObject.name}. :(")
        }
        return jumpable
    }

    private fun canJumpOver (realWorldObject: RealWorldObject): Boolean =
        (realWorldObject.height <= this.height && realWorldObject.length <= this.height)

    private fun printLocationHistory()
    {
        println()
        print("LocationHistory: ")

        if (locationHistory.size == 0) {
            print(" empty ")
            println()
        }
        else {
            locationHistory.forEachIndexed { i, e -> print("[$i] = ${e.name} ") }
        }
        println()
    }

    override fun toString(): String ="${this.javaClass.name}(${::name.name}= ${this.name}, ${::health.name} =${this.health}, ${::location.name}=${this.location.name}, ${::height.name}= ${this.height})"
}