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
})