package domain.realWorldObject

class LargeBoxFactory : IRealWorldObjectFactory {
    override fun create(): IRealWorldObject = RealWorldObject("large box", "This is a really large and heavy looking box.", 51, 1, 1, 50)
}
