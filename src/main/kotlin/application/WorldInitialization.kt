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

    fun init() {
        //https://stackoverflow.com/questions/5763747/h2-in-memory-database-table-not-found
        Database.connect("jdbc:h2:./baseObjects.db;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.createDatabase("StoryGen")
            SchemaUtils.create(WorldObjects)

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
        }
    }

    object WorldObjects : UUIDTable() {
        val name = varchar("name", 50)
        var description = varchar("description", 200)
        var height = integer("height")
        var width = integer("width").nullable()
        var length = integer("length").nullable()
        var weight = integer("weight").nullable()
    }
}