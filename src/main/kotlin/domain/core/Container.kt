package domain.core

import domain.realWorldObject.WorldObject

interface Container {
    val containingObjects: MutableSet<WorldObject>

}
