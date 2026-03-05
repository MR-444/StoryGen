package domain.realWorldObject

import domain.core.Entity
import domain.core.PhysicalBody

sealed interface WorldObject : Entity, PhysicalBody
