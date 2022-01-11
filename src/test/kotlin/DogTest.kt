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

    test("toString() should correct") {
        fido.toString() shouldBe "Dog(name=Fido, health=100, location=PlayGround, height=21.0)"
    }
})