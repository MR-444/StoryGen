package domain.realWorldObject

class RealWorldObjectFactory {

    fun create(realWorldObject: RealWorldObject): IRealWorldObject {
        return when (realWorldObject) {
            RealWorldObject.LargeBox -> LargeBox()
            RealWorldObject.SmallBox -> SmallBox()
            RealWorldObject.WaterLilies -> WaterLilies()
        }
    }

}