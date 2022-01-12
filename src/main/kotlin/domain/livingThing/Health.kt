package domain.livingThing

enum class Health(val text: String) {
    Healthy("healthy"),
    Injured("injured"),
    HeavilyInjured("heavily injured"),
    Crippled("crippled"),
    NearlyDead("nearly dead"),
    Dead("dead")
}
