fun main() {
    // instantiate objects
    val playGround = LocationFactory("PlayGround")
    val pond = LocationFactory("Pond")
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

    // =======================================================================
    // let's travel the dog between 2 locations
    println()
    fido.moveToAndBack(pond, null)

    // let's travel the dog between 2 locations
    // and put an obstacle between the locations.
    val smallBox = RealWorldObject(name = "small box", height = 1, width = 1, length = 1)

    fido.moveToAndBack(pond, smallBox)

    // let's travel the dog between 2 locations
    // and put an obstacle between the locations which is too big to jump over.
    val bigBox = RealWorldObject(name = "big box", height = 51, width = 1, length = 1)

    fido.moveToAndBack(pond, bigBox)
}



