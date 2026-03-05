package domain.livingThing

enum class Health(val text: String, val range: IntRange) {
    Healthy("healthy", 100..Int.MAX_VALUE),
    Injured("injured", 75..99),
    HeavilyInjured("heavily injured", 50..74),
    Crippled("crippled", 25..49),
    NearlyDead("nearly dead", 1..24),
    Dead("dead", Int.MIN_VALUE..0);

    companion object {
        fun fromValue(value: Int): Health {
            return entries.find { value in it.range } ?: Dead
        }
    }
}
