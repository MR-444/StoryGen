package domain.location

import domain.realWorldObject.WorldObject
import java.util.UUID

data class BaseLocation(
    override val id: String = UUID.randomUUID().toString(),
    override var name: String,
    override var description: String?,
    override val containingObjects: MutableSet<WorldObject> = mutableSetOf(),
    override val exits: MutableMap<Direction, Location> = mutableMapOf()
) : Location {
    override fun toString(): String {
        return "BaseLocation(id=$id, name=$name, description=$description, contains=${containingObjects.size} objects, exits=${exits.keys})"
    }
}
