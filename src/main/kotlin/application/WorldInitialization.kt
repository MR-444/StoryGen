package application

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Initialize the World:
 *
 * 1. Locations
 * 2. RealWorldObjects
 * 3. Living Beings
 *
 *
 *
 */

class WorldInitialization {

    fun init() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {

            val stPeteId = Cities.insert {
                it[name] = "St. Petersburg"
            }

            println("Cities: ${Cities.selectAll()}")
        }

    }

    object Cities : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 50)
    }

    data class City(val id: Int, val name: String)
}