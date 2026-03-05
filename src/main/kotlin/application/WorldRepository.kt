package application

import domain.livingThing.Character
import domain.location.BaseLocation
import domain.location.Direction
import domain.location.Location
import domain.realWorldObject.RealWorldObject
import domain.realWorldObject.WorldObject
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class WorldRepository {

    /**
     * Loads the entire world state from the database.
     * Returns a map of Locations indexed by their name.
     */
    fun loadWorld(): Map<String, Location> {
        return transaction {
            // 1. Load Locations
            val locationCache = WorldInitialization.Locations.selectAll().associate { row ->
                val id = row[WorldInitialization.Locations.id].value
                id to BaseLocation(
                    id = id.toString(),
                    name = row[WorldInitialization.Locations.name],
                    description = row[WorldInitialization.Locations.description],
                )
            }

            val nameToLocation = locationCache.values.associateBy { it.name }

            // 2. Load World Objects and assign them to locations
            WorldInitialization.WorldObjects.selectAll().forEach { row ->
                val obj = RealWorldObject(
                    id = row[WorldInitialization.WorldObjects.id].value.toString(),
                    name = row[WorldInitialization.WorldObjects.name],
                    description = row[WorldInitialization.WorldObjects.description],
                    height = row[WorldInitialization.WorldObjects.height],
                    width = row[WorldInitialization.WorldObjects.width] ?: 1.0,
                    length = row[WorldInitialization.WorldObjects.length] ?: 1.0,
                    weight = row[WorldInitialization.WorldObjects.weight] ?: 1.0,
                    tags = row[WorldInitialization.WorldObjects.tags].split(",")
                        .filter { it.isNotBlank() }
                        .toMutableSet(),
                )
                
                val locId = row[WorldInitialization.WorldObjects.locationId]?.value
                if (locId != null) {
                    locationCache[locId]?.containingObjects?.add(obj)
                }
            }

            // 3. Load Exits (World Graph)
            WorldInitialization.Exits.selectAll().forEach { row ->
                val from = locationCache[row[WorldInitialization.Exits.fromLocation].value]
                val to = locationCache[row[WorldInitialization.Exits.toLocation].value]
                val dirStr = row[WorldInitialization.Exits.direction]
                
                if (from != null && to != null) {
                    val direction = Direction.valueOf(dirStr)
                    from.exits[direction] = to
                }
            }

            nameToLocation
        }
    }

    /**
     * Loads a specific character by name.
     */
    fun loadCharacter(name: String, world: Map<String, Location>): Character? {
        return transaction {
            WorldInitialization.LivingThings.select { WorldInitialization.LivingThings.name eq name }
                .map { row ->
                    val locId = row[WorldInitialization.LivingThings.locationId]?.value
                    val loc = locId?.let { id -> 
                        // Find location by UUID in the already loaded world map
                        world.values.find { it.id == id.toString() } 
                    } ?: world.values.first()

                    Character(
                        id = row[WorldInitialization.LivingThings.id].value.toString(),
                        name = row[WorldInitialization.LivingThings.name],
                        type = row[WorldInitialization.LivingThings.type],
                        description = row[WorldInitialization.LivingThings.description],
                        health = row[WorldInitialization.LivingThings.health],
                        height = row[WorldInitialization.LivingThings.height],
                        traits = row[WorldInitialization.LivingThings.traits].split(",")
                            .filter { it.isNotBlank() }
                            .toMutableSet(),
                        location = loc,
                    )
                }.firstOrNull()
        }
    }
}
