package domain.action

import domain.core.Entity
import domain.location.Location

data class Event(val message: String, val entity: Entity? = null, val location: Location? = null)
