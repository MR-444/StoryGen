package domain.livingThing

import domain.action.Event
import domain.core.Entity
import domain.core.PhysicalBody
import domain.location.Direction
import domain.location.Location
import domain.realWorldObject.WorldObject
import java.util.*

data class Character(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String,
    val type: String,
    override val description: String,
    var health: Int,
    var location: Location,
    override var height: Double,
    override var width: Double = 1.0,
    override var length: Double = 1.0,
    override var weight: Double = 70.0,
    val traits: MutableSet<String> = mutableSetOf(),
) : Entity, PhysicalBody {

    val locationHistory = ArrayDeque<Location>()

    fun healthToLiteral() = Health.fromValue(health).text

    fun say(s: String) = Event("$name ($type) says: $s", this, location)

    fun lookAt(worldObject: WorldObject) =
        Event("$name ($type) looks at ${worldObject.name}. ${worldObject.description}", this, location)

    fun lookAround() =
        Event("$name ($type) looks around and sees: ${location.containingObjects.joinToString(", ") { it.name }}", this, location)

    fun move(direction: Direction, obstacle: WorldObject? = null): List<Event> = buildList {
        val destination = location.exits[direction]

        if (destination == null) {
            add(Event("$name ($type) bumped into something trying to go $direction.", this@Character, location))
            return@buildList
        }

        if (obstacle != null) {
            add(Event("Command: $name ($type) move $direction and jump over ${obstacle.name}.", this@Character, location))
            val jumpEvent = jumpOver(obstacle)
            add(jumpEvent)
            
            if (canJumpOver(obstacle)) {
                add(performMove(destination, direction))
            }
        } else {
            add(Event("Command: $name ($type) move $direction.", this@Character, location))
            add(performMove(destination, direction))
        }
    }

    private fun performMove(destination: Location, direction: Direction): Event {
        locationHistory.add(location)
        val event = Event("$name ($type) moves to the $direction towards ${destination.name}.", this, destination)
        location = destination
        locationHistory.add(destination)
        return event
    }

    fun jumpOver(worldObject: WorldObject): Event {
        val jumpable = canJumpOver(worldObject)
        return if (jumpable) {
            Event("$name ($type) jumped over ${worldObject.name} effortlessly.", this, location)
        } else {
            Event("$name ($type) is too small to jump over ${worldObject.name}.", this, location)
        }
    }

    private fun canJumpOver(worldObject: WorldObject): Boolean =
        (worldObject.height <= height && worldObject.length <= height)

    fun printLocationHistory(): Event {
        val history = if (locationHistory.isEmpty()) "empty" else locationHistory.mapIndexed { i, e -> "[$i] = ${e.name}" }.joinToString(" ")
        return Event("Location History for $name: $history", this, location)
    }

    fun backTrack(): List<Event> = buildList {
        add(Event("Character $name is backtracking:", this@Character, location))
        locationHistory.reversed().forEach { n ->
            add(Event("Visited: ${n.name}", this@Character, location))
        }
    }
}
