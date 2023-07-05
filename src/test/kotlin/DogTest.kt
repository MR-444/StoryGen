import domain.livingThing.Dog
import domain.location.Location
import domain.location.LocationFactory
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe



class DogTest : FunSpec({
    // initialize
    val playGround = LocationFactory().create(Location.PlayGround)
    val pond = LocationFactory().create(Location.Pond)

    val fido = Dog(
        name = "Fido",
        health = 100,
        location = playGround,
        height = 21.0
    )

    test("moveToAndBack() should give 3 locations") {
        fido.moveToAndBack(pond, null)
        fido.locationHistory.size shouldBe 3
    }

    test("moveTo() should ??") {
    }

    test("getSuccessorLocation() should ??") {
    }

    test("canJumpOver() should ??") {
    }

    test("printLocationHistory() should ??") {
    }

    test("toString() should be correct.") {
        fido.toString() shouldBe "domain.livingThing.Dog(name=Fido, health=100, location=Playground, height=21.0)"
    }

    test("bark() should be correct.") {
        fido.bark shouldBe "Woof!"
    }

    test("healthToLiteral() should be correct.") {
        fido.healthToLiteral() shouldBe "healthy"
    }

    test("lookAround() should be correct.") {
        fido.lookAround() shouldBe "Fido looks around."
    }

    test("say() should be correct.") {
        fido.say("Hello") shouldBe "Fido says: Hello"
    }

    test("moveToAndBack() should be correct.") {
        fido.moveToAndBack(pond, null) shouldBe "Fido runs to the Pond."
    }

    // test if the complete class is correct
    test("Dog should be correct.") {
        fido.name shouldBe "Fido"
        fido.health shouldBe 100
        fido.location shouldBe playGround
        fido.height shouldBe 21.0
    }

})