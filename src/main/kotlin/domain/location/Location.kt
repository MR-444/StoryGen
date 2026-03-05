package domain.location

import domain.core.Container
import domain.core.Entity
import domain.realWorldObject.WorldObject

sealed interface Location : Entity, Container {
    val exits: MutableMap<Direction, Location>
    
    // Default implementation to connect locations two-way
    fun connect(direction: Direction, destination: Location, reverseDirection: Direction? = null) {
        exits[direction] = destination
        if (reverseDirection != null) {
            destination.exits[reverseDirection] = this
        }
    }
}