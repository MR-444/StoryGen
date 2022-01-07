import java.util.LinkedList

data class Dog(
    override val name: String,
    override var health: Int,
    override var location: ILocation,
    var height: Double
) : Monster(name, health, location) {

    override val locationHistory = LinkedList<ILocation>()

    val bark = "woof"

    fun say(s: String) = println(s)

    override fun moveToAndBack(destination: ILocation, realWorldObject: RealWorldObject?) {

        println()

        if (realWorldObject == null) {

            println("Command: $name move to ${destination.name} and back.")
            say("I am starting at the ${location.name}.")

            printLocationHistory()

            moveTo(destination)
            runBack()
        } else {   // move to another location with an obstacle between
            println("Command: $name move to ${destination.name} and back.")
            println("And jump over the ${realWorldObject.name}.")
            say("I am starting at the ${location.name}.")

            if (jumpOver(realWorldObject)) {
                moveTo(destination)
                runBack()
            }
        }
    }

    private fun runBack() {
        // run back
        // add the new destination which was the start.
        say("I am running back to the ${getSuccessorLocation(location).name}.")

        location = getSuccessorLocation(location)
        locationHistory.add(location)

        say("I am at ${location.name} and have visited the ${getSuccessorLocation(location).name}.")
    }

    private fun moveTo(destination: ILocation) {
        // just move to another location
        //remember the actual starting location
        locationHistory.add(location)

        say("I am running to the ${destination.name}.")

        location = destination
        locationHistory.add(destination)
        say("I am now at ${destination.name}.")
    }

    private fun getSuccessorLocation(location: ILocation): ILocation {
        return locationHistory[locationHistory.lastIndexOf(location) - 1]
    }

    override fun jumpOver(realWorldObject: RealWorldObject): Boolean {
        val jumpable = canJumpOver(realWorldObject)
        if (jumpable) {
            say("Jumping over ${realWorldObject.name} ... whee. :)")
        } else {
            say("I am to small to jump over ${realWorldObject.name}. :(")
        }
        return jumpable
    }

    private fun canJumpOver(realWorldObject: RealWorldObject): Boolean =
        (realWorldObject.height <= height && realWorldObject.length <= height)

    fun printLocationHistory() {
        println()
        print("LocationHistory: ")

        if (locationHistory.size == 0) {
            print(" empty ")
            println()
        } else {
            locationHistory.forEachIndexed { i, e -> print("[$i] = ${e.name} ") }
        }
        println()
    }

    override fun toString(): String =
        "${javaClass.name}(${::name.name}= ${name}, ${::health.name} =${health}, ${::location.name}=${location.name}, ${::height.name}= ${height})"
}