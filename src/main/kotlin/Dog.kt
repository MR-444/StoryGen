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
        if (realWorldObject == null ) {
            // just move to another location
            println("Command: ${name} move to ${destination.name} and back.")

            say("I am at the ${location.name}.")
            //remember the actual location
            locationHistory.add(location)

            // todo apply location dependent status changes -- if any exists
            say("I am running to the ${destination.name}.")

            this.location= destination
            say("I am at ${this.location.name}.")
            //remember the destination location now the old one...
            locationHistory.push(destination)

            say("I am running back to the ${locationHistory.pop()?.name}.")
        }
        else
        {   // move to another location with an obstacle between
            println()
            println("Command: ${this.name} move to ${destination.name} and back. And jump over the ${realWorldObject.name}.")
            if (jumpOver(realWorldObject)) {

                say("I am running back to the ${locationHistory.pop()?.name}.")
                //remember the destination location now the old one...
                locationHistory.push(destination)
            }
        }
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

    override fun toString(): String ="${this.javaClass.name}(${::name.name}= ${this.name}, ${::health.name} =${this.health}, ${::location.name}=${this.location.name}, ${::height.name}= ${this.height})"
}