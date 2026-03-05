package domain.core

sealed interface Entity {
    val id: String
    val name: String
    val description: String?
}
