import domain.livingThing.Dog
import domain.location.Location
import domain.location.LocationFactory
import domain.realWorldObject.RealWorldObject
import domain.realWorldObject.RealWorldObjectFactory

fun main() {
    // instantiate objects
    val playGround = LocationFactory().create(Location.PlayGround)
    val pond = LocationFactory().create(Location.Pond)

    val fido = Dog(
        name = "Fido",
        health = 100,
        location = playGround,
        height = 21.0
    )

    // story begins.
    println("All begins with the ${fido.javaClass.simpleName}...")

    println(fido)

    println("The ${fido.javaClass.simpleName} called ${fido.name} makes ${fido.bark}.")
    println("And is ${fido.healthToLiteral()}.")

    // move the dog around in one location.
    println()
    println("${fido.name} runs happily around in the ${playGround.name}.")

    // =======================================================================
    // let's travel the dog between 2 locations
    println()
    fido.moveToAndBack(pond, null)

    // let's travel the dog between 2 locations
    // and put an obstacle between the locations.
    val smallBox = RealWorldObjectFactory().create(RealWorldObject.SmallBox)
    fido.moveToAndBack(pond, smallBox)

    // let's travel the dog between 2 locations
    // and put an obstacle between the locations which is too big to jump over.
    val largeBox = RealWorldObjectFactory().create(RealWorldObject.LargeBox)
    fido.moveToAndBack(pond, largeBox)

    //show travel history
    fido.say("I ran around a lot: ")
    fido.printLocationHistory()

    fido.say("I am tracking the whole locations back now.")
    fido.backTrack()
}



