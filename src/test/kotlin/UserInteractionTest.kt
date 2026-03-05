import application.WorldInitialization
import application.WorldRepository
import domain.location.Direction
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.io.File

/**
 * This test serves as a "User Verification System".
 * It runs through a complete lifecycle of the application:
 * 1. Database Initialization
 * 2. Seeding
 * 3. Loading from Persistence
 * 4. Character Movement & Logic
 * 5. Verification of State
 */
class UserInteractionTest : FunSpec({

    val dbFile = File("baseObjects.mv.db")

    beforeSpec {
        // Ensure a clean start for the test
        if (dbFile.exists()) dbFile.delete()
    }

    test("Full Story Flow Verification") {
        val init = WorldInitialization()
        val repo = WorldRepository()

        // Phase 1: Infrastructure
        init.initSchema()
        dbFile.exists() shouldBe true

        // Phase 2: Seeding
        init.seedTestWorld()

        // Phase 3: Loading
        val world = repo.loadWorld()
        world.size shouldBe 2
        world.keys shouldBe setOf("Playground", "Pond")

        val fido = repo.loadCharacter("Fido", world)
        fido shouldNotBe null
        fido?.name shouldBe "Fido"
        fido?.location?.name shouldBe "Playground"

        // Phase 4: Interaction
        // Move Fido to the Pond (East)
        val moveResult = fido!!.move(Direction.EAST)
        moveResult.any { it.message.contains("moves to the EAST towards Pond") } shouldBe true
        fido.location.name shouldBe "Pond"

        // Check if Pond contains Water Lilies (Persisted Object)
        fido.location.containingObjects.any { it.name == "water lilies" } shouldBe true

        // Move Fido back (West)
        fido.move(Direction.WEST)
        fido.location.name shouldBe "Playground"

        // Phase 5: Persistence check (Backtracking)
        fido.locationHistory.size shouldBe 4 // Start -> Pond -> Pond -> Playground
        fido.locationHistory.first().name shouldBe "Playground"
        fido.locationHistory.last().name shouldBe "Playground"

        println("User Interaction Test Passed Successfully!")
    }
})
