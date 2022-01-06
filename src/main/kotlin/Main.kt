fun main() {
    // instantiate objects
    val playGround = LocationFactory("PlayGround")
    val fido = Dog(
        name = "Fido",
        health = 100,
        location = playGround,
        height = 21.0
    )

    // story begins.
    println("All begins with the ${fido.javaClass.name}...")

    println(fido)

    println("The ${fido.javaClass.name} called ${fido.name} makes ${fido.bark}.")
    println("And is ${fido.healthToLiteral()}.")

    // move the dog around in one location.
    println()
    println("${fido.name} runs happily around in the ${playGround.name}.")

    // let's travel the dog between 2 locations
    val duckPond = LocationFactory("Pond")

    println()
    println("Command: ${fido.name} move to ${duckPond.name} and back.")
    fido.moveToAndBack(duckPond)

    fido.say("I am at ${fido.location.name} and have visited the ${fido.giveLastVisitedLocation()}")

    // let's travel the dog between 2 locations
    // and put an obstacles
    val smallBox = RealWorldObject(name = "small box", height = 1, width = 1, length = 1)

    fido.jumpOver(duckPond, smallBox)

    // let's travel the dog between 2 locations
    // and put an obstacles
    val bigBox = RealWorldObject(name = "big box", height = 51, width = 1, length = 1)

    fido.jumpOver(duckPond, bigBox)
}



