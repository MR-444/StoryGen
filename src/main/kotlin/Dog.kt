data class Dog(
    override val name: String,
    override var health: Int,
    override var location: Location,
    var height: Double) : Monster(name, health, location) {

    override val locationHistory = LocationStack()

    val bark = "woof"

    fun say(s: String) {
        println (s)}

    fun giveLastVisitedLocation() = locationHistory.peek()?.location?.name

    override fun moveToAndBack(destination: Location) {
        //remember the actual location
        say("I am at the ${location.name}.")
        locationHistory.push(TimeLocation(location))

        // todo apply location dependent status changes -- if any exists
        say("I am running to the ${destination.name}.")

        say("I am running back to the ${locationHistory.pop()?.location?.name}.")
        //remember the destination location now the old one...
        locationHistory.push(TimeLocation(destination))
    }

    override fun jumpOver(location: Location, realWorldObject: RealWorldObject) {
        println()
        println("Command: ${this.name} move to ${location.name} and back. And jump over the ${realWorldObject.name}.")

        if (this.canJumpOver(realWorldObject)) {
            this.say("Jumping... whee. :)")
            this.moveToAndBack(location)
        }
        else {
            this.say("I am to small. :(")
        }
    }

    private fun canJumpOver (realWorldObject: RealWorldObject): Boolean =
        (realWorldObject.height <= this.height && realWorldObject.length <= this.height)
}