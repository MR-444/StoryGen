package domain.realWorldObject

class WaterLilies(
    override val name: String = "water lilie",
    override var description: String = "A neat looking water plant.",
    override var height: Int = 2,
    override var width: Int = 2,
    override var length: Int = 1,
    override var weight: Int = 1,
) : IRealWorldObject