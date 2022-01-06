data class Memory(val location: ILocation){
    private val locationSet = mutableSetOf<ILocation>()

    fun add (location: ILocation) = locationSet.add(location)

    fun find (location: ILocation) =  locationSet.contains(location)
}
