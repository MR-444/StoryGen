package domain.realWorldObject

class SandBox(
    override val name: String = "sand box",
    override var description: String = "This pit is bordered with board filled with sand for small children to play with.",
    override var height: Int = 1,
    override var width: Int = 3,
    override var length: Int = 3,
    override var weight: Int = 500,
) : IRealWorldObject