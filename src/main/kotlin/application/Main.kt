package application

import domain.location.Direction

fun main(args: Array<String>) {

    // 1. Initialize DB Infrastructure
    val worldInit = WorldInitialization()
    worldInit.initSchema()

    // 2. Seed if requested
    if (args.contains("--seed")) {
        println("Seeding database with test data...")
        worldInit.seedTestWorld()
    }

    // 3. Load entire world from persistence
    val repository = WorldRepository()
    val world = repository.loadWorld()
    
    if (world.isEmpty()) {
        println("The world is empty! Run with --seed to populate it.")
        return
    }

    // 4. Load Actor
    val fido = repository.loadCharacter("Fido", world)
    
    if (fido == null) {
        println("Could not find Fido in the repository.")
        return
    }

    val playground = world["Playground"] ?: fido.location

    // Setup Random Encounters Registry (Keeping it in memory for now as a dynamic component)
    val encounters = RandomEncounters().apply {
        world.values.forEach { loc ->
            loc.containingObjects.forEach { obj ->
                register { RealWorldObjectEncounter(obj) }
            }
        }
        val aria = repository.loadCharacter("Aria", world)
        if (aria != null) {
            register { CharacterEncounter(aria) }
        }
    }

    // story begins.
    println("\n--- The Persistence-Driven Story Begins ---")
    println("Fido the ${fido.type} wakes up in the ${fido.location.name}.")

    // look around
    println(fido.lookAround().message)

    // travel according to the logical graph in the DB
    println("\nMoving East...")
    fido.move(Direction.EAST).forEach { println(it.message) }

    // Random Encounter!
    println("\nSomething unexpected happens...")
    val incident = encounters.generateEncounter()
    println(incident?.interact() ?: "The world remains quiet.")

    println("\nHeading back...")
    fido.move(Direction.WEST).forEach { println(it.message) }
    
    println("\nLocation history for Fido: ${fido.locationHistory.map { it.name }}")
}
