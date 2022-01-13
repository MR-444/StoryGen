package domain.realWorldObject

class LargeBox(
    override val name: String = "big box",
    override var description: String = "This is a really large and heavy looking box.",
    override var height: Int = 51,
    override var width: Int = 1,
    override var length: Int = 1,
    override var weight: Int = 50,
) : IRealWorldObject
