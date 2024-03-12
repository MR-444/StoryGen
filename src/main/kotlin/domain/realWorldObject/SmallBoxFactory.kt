package domain.realWorldObject

class SmallBoxFactory : IRealWorldObjectFactory {
    override fun create(): IRealWorldObject = RealWorldObject(
        name = "small box",
        description = "This is a small box.",
        height = 1,
        width = 1,
        length = 1,
        weight = 1
    )
}
