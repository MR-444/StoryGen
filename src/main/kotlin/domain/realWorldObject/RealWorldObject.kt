package domain.realWorldObject

data class RealWorldObject(
    override val name: String,
    override var description: String,
    override var height: Int,
    override var width: Int,
    override var length: Int,
    override var weight: Int
) : IRealWorldObject
