package domain.core

sealed interface PhysicalBody {
    var height: Double
    var width: Double
    var length: Double
    var weight: Double
}
