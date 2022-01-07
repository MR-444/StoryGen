import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MonsterTest : FunSpec({

    // initialize
    val playGround = LocationFactory("PlayGround")

    val fido = Dog(
        name = "Fido",
        health = 100,
        location = playGround,
        height = 21.0
    )

    test("healthToLiteral() should give a literal") {
        fido.healthToLiteral() shouldBe "healthy"
    }

    test("healthToLiteral() should give Injured") {
        fido.health = 76
        fido.healthToLiteral() shouldBe "injured"
    }

    test("healthToLiteral() should give HeavilyInjured") {
        fido.health = 70
        fido.healthToLiteral() shouldBe "heavily injured"
    }

    test("healthToLiteral() should give Crippled") {
        fido.health = 27
        fido.healthToLiteral() shouldBe "crippled"
    }

    test("healthToLiteral() should give NearlyDead") {
        fido.health = 5
        fido.healthToLiteral() shouldBe "nearly dead"
    }

    test("healthToLiteral() should give Dead") {
        fido.health = 0
        fido.healthToLiteral() shouldBe "dead"
    }

})