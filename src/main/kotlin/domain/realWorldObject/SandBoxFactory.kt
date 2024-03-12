package domain.realWorldObject

class SandBoxFactory : IRealWorldObjectFactory {
    override fun create(): IRealWorldObject = RealWorldObject("sand box", "This pit is bordered with board filled with sand for small children to play with.",
        1, 3, 3, 500)
}
