class LocationStack{
    private val elements: MutableList<TimeLocation> = mutableListOf()
    private fun isEmpty() = elements.isEmpty()

    fun push(item: TimeLocation) = elements.add(item)

    fun pop() : TimeLocation? {
        val item = elements.lastOrNull()
        if (!isEmpty()){
            elements.removeAt(elements.size -1)
        }
        return item
    }
    fun peek() : TimeLocation? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}
