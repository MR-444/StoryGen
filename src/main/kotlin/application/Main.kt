package application

import domain.livingThing.Dog
import domain.location.Location
import domain.location.LocationFactory
import domain.realWorldObject.LargeBoxFactory
import domain.realWorldObject.RealWorldObject
import domain.realWorldObject.SmallBoxFactory


fun main() {

    WorldInitialization().also {
        it.create()
    }

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

    // look around
    fido.lookAround()

    // =======================================================================
    // let's travel the dog between 2 locations
    println()
    fido.moveToAndBack(pond, null)

    // let's travel the dog between 2 locations
    // and put an obstacle between the locations.
    val smallBox = SmallBoxFactory().create()
    fido.moveToAndBack(pond, smallBox)

    // let's travel the dog between 2 locations
    // and put an obstacle between the locations which is too big to jump over.
    val largeBox = LargeBoxFactory().create()

    fido.moveToAndBack(pond, largeBox)

    //show travel history
    println()
    fido.say("I ran around a lot: ")
    fido.printLocationHistory()

    println()
    fido.say("I am tracking the whole locations back now.")
    fido.backTrack()
}



