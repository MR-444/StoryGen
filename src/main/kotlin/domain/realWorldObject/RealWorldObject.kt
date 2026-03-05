package domain.realWorldObject

import java.util.UUID

data class RealWorldObject(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String,
    override val description: String,
    override var height: Double,
    override var width: Double,
    override var length: Double,
    override var weight: Double,
    val tags: MutableSet<String> = mutableSetOf()
) : WorldObject
