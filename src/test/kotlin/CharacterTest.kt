import domain.livingThing.Character
import domain.location.BaseLocation
import domain.location.Direction
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CharacterTest : FunSpec({
    // initialize
    val playGround = BaseLocation(name = "Playground", description = "A small playground.")
    val pond = BaseLocation(name = "Pond", description = "A lovely small pond.")
    
    playGround.connect(Direction.EAST, pond, Direction.WEST)

    val fido = Character(
        name = "Fido",
        type = "Dog",
        description = "A loyal companion.",
        health = 100,
        location = playGround,
        height = 21.0
    )

    test("move() should update location history") {
        fido.move(Direction.EAST)
        fido.locationHistory.size shouldBe 2 // Start + Destination
        fido.location shouldBe pond
    }

    test("healthToLiteral() should be correct.") {
        fido.healthToLiteral() shouldBe "healthy"
        fido.health = 0
        fido.healthToLiteral() shouldBe "dead"
    }

    test("lookAround() should include type") {
        val result = fido.lookAround().message
        result.contains("Fido (Dog) looks around and sees:") shouldBe true
    }

    test("say() should include type") {
        fido.say("Hello").message shouldBe "Fido (Dog) says: Hello"
    }

    test("Princess should work same as Dog") {
        val aria = Character(
            name = "Aria",
            type = "Princess",
            description = "Heir to the throne.",
            health = 100,
            location = playGround,
            height = 165.0
        )
        aria.move(Direction.EAST)
        aria.location shouldBe pond
        aria.say("Greetings").message shouldBe "Aria (Princess) says: Greetings"
    }
})
