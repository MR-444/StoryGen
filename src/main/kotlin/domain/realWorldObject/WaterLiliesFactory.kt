package domain.realWorldObject

class WaterLiliesFactory : IRealWorldObjectFactory {
    override fun create(): IRealWorldObject = RealWorldObject(
        name = "water lilies",
        description = "A neat looking water plant.",
        height = 2,
        width = 2,
        length = 1,
        weight = 1
    )
}
