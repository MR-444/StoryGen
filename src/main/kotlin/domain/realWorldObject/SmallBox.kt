package domain.realWorldObject

class SmallBox(
    override val name: String = "small box",
    override var description: String = "This is a small box.",
    override var height: Int = 1,
    override var width: Int = 1,
    override var length: Int = 1,
    override var weight: Int = 1,
) : IRealWorldObject