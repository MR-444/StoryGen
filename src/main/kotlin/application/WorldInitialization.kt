package application

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

//import org.slf4j.LoggerFactory

/**
 * Initialize the World:
 *
 * 1. Locations
 * 2. RealWorldObjects
 * 3. Living Beings
 *
 */

class WorldInitialization {

    /**
     * Create the data in the database.
     */
    fun create() {
        //https://stackoverflow.com/questions/5763747/h2-in-memory-database-table-not-found
        Database.connect("jdbc:h2:./baseObjects;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.createDatabase("StoryGen")
            SchemaUtils.create(WorldObjects, LivingThings, Locations)


            // -----------------WorldObjects------------------------
            WorldObjects.insert {
                it[name] = "small box"
                it[description] = "This is a small box."
                it[height] = 1
                it[width] = 1
                it[length] = 1
                it[weight] = 1
            }

            WorldObjects.insert {
                it[name] = "large box"
                it[description] = "This is a really large and heavy looking box."
                it[height] = 51
                it[width] = 1
                it[length] = 1
                it[weight] = 50
            }

            WorldObjects.insert {
                it[name] = "water lilie"
                it[description] = "A neat looking water plant."
                it[height] = 2
                it[width] = 2
                it[length] = 1
                it[weight] = 1
            }

            // -----------------LivingBeings------------------------

            LivingThings.insert {
                it[name] = "dog"
                it[health] = 100
                it[description] = "A four legged animal."
            }

            // -----------------LivingBeings------------------------
            Locations.insert {
                it[name] = "playground"
                it[description] = "A small playground."
            }

            Locations.insert {
                it[name] = "pond"
                it[description] = "A lovely small pond."
            }

        }
    }

    // load all data structures into memory.
    fun init() {

    }

    //
    // The tables should contain only flat data!
    // while thinking, I began to design the whole relations.
    // But the building and association process should occur in the OO world not in the ERM world.
    //
    // Todo: How I persist all the relations: think later about it, if you need it.
    //

    object WorldObjects : UUIDTable() {
        val name = varchar("name", 50)
        var description = varchar("description", 200)
        var height = integer("height")
        var width = integer("width").nullable()
        var length = integer("length").nullable()
        var weight = integer("weight").nullable()
    }

    object LivingThings : UUIDTable() {
        val name = varchar("name", 50)
        var description = varchar("description", 200)
        var health = integer("health")
    }

    object Locations : UUIDTable() {
        val name = varchar("name", 50)
        var description = varchar("description", 200)
    }
}