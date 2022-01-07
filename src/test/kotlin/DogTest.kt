import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DogTest : FunSpec({

    // initialize
    val playGround = LocationFactory("PlayGround")
    val pond = LocationFactory("Pond")

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

    test("toString() should ??") {
    }
})