package domain.core

import domain.realWorldObject.WorldObject

sealed interface Container {
    val containingObjects: MutableSet<WorldObject>

}
