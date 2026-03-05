package application

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Manages Database Schema and Initial Data Seeding.
 */
class WorldInitialization {

    /**
     * Connect to the database and initialize the schema (Tables).
     * This creates an empty world ready for any data.
     */
    fun initSchema() {
        Database.connect("jdbc:h2:./baseObjects;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.createDatabase("StoryGen")
            SchemaUtils.create(WorldObjects, LivingThings, Locations, Exits)
        }
    }

    /**
     * Seeds the database with fixed test data (Fido, Aria, boxes, and connections).
     * Only call this for testing or demonstration purposes.
     */
    fun seedTestWorld() {
        transaction {
            addLogger(StdOutSqlLogger)

            // Clear existing data to avoid primary key conflicts during re-seeding
            Exits.deleteAll()
            LivingThings.deleteAll()
            WorldObjects.deleteAll()
            Locations.deleteAll()

            // -----------------Locations------------------------
            val playgroundId = Locations.insertAndGetId {
                it[name] = "Playground"
                it[description] = "A small playground."
            }

            val pondId = Locations.insertAndGetId {
                it[name] = "Pond"
                it[description] = "A lovely small pond."
            }

            // -----------------Exits (World Graph)------------------------
            Exits.insert {
                it[fromLocation] = playgroundId
                it[toLocation] = pondId
                it[direction] = "EAST"
            }
            Exits.insert {
                it[fromLocation] = pondId
                it[toLocation] = playgroundId
                it[direction] = "WEST"
            }

            // -----------------WorldObjects------------------------
            WorldObjects.insert {
                it[name] = "sand box"
                it[description] = "A place to play with sand."
                it[height] = 0.5
                it[width] = 2.0
                it[length] = 2.0
                it[weight] = 100.0
                it[tags] = "sand,play"
                it[locationId] = playgroundId
            }

            WorldObjects.insert {
                it[name] = "small box"
                it[description] = "This is a small box."
                it[height] = 1.0
                it[width] = 1.0
                it[length] = 1.0
                it[weight] = 1.0
                it[tags] = "container,wood"
                it[locationId] = playgroundId
            }

            WorldObjects.insert {
                it[name] = "large box"
                it[description] = "This is a really large box."
                it[height] = 51.0
                it[width] = 1.0
                it[length] = 1.0
                it[weight] = 50.0
                it[tags] = "heavy,wood"
                it[locationId] = playgroundId
            }

            WorldObjects.insert {
                it[name] = "water lilies"
                it[description] = "A neat looking water plant."
                it[height] = 2.0
                it[width] = 2.0
                it[length] = 1.0
                it[weight] = 1.0
                it[tags] = "plant,water"
                it[locationId] = pondId
            }

            // -----------------LivingThings (Characters)------------------------
            LivingThings.insert {
                it[name] = "Fido"
                it[type] = "Dog"
                it[health] = 100
                it[description] = "A loyal companion."
                it[height] = 21.0
                it[traits] = "barks,loyal"
                it[locationId] = playgroundId
            }

            LivingThings.insert {
                it[name] = "Aria"
                it[type] = "Princess"
                it[health] = 100
                it[description] = "Heir to the throne."
                it[height] = 165.0
                it[traits] = "royal,charismatic"
                it[locationId] = pondId
            }
        }
    }

    object WorldObjects : UUIDTable() {
        val name = varchar("name", 50)
        var description = varchar("description", 200)
        var height = double("height")
        var width = double("width").nullable()
        var length = double("length").nullable()
        var weight = double("weight").nullable()
        val tags = varchar("tags", 255).default("")
        val locationId = reference("location_id", Locations).nullable()
    }

    object LivingThings : UUIDTable() {
        val name = varchar("name", 50)
        val type = varchar("type", 50)
        var description = varchar("description", 200)
        var health = integer("health")
        var height = double("height").default(100.0)
        val traits = varchar("traits", 255).default("")
        val locationId = reference("location_id", Locations).nullable()
    }

    object Locations : UUIDTable() {
        val name = varchar("name", 50)
        var description = varchar("description", 200)
    }

    object Exits : UUIDTable() {
        val fromLocation = reference("from_location_id", Locations)
        val toLocation = reference("to_location_id", Locations)
        val direction = varchar("direction", 10) // e.g., "NORTH", "SOUTH"
    }
}