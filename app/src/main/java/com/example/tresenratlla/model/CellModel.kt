package com.example.tresenratlla.model

/**
 * Representa una casella del tauler.
 *
 * És un model immutable: quan es marca una casella no es modifica aquest
 * objecte, sinó que es crea una còpia amb el símbol corresponent mitjançant
 * [copy]. Això permet que Compose detecti el canvi d'estat i redibuixi la UI.
 *
 * @property id identificador estable de la casella (0..8).
 * @property position posició de la casella dins del tauler.
 * @property symbol símbol que ocupa la casella, o `null` si està buida.
 */
data class CellModel(
    val id: Int,
    val position: Int = id,
    val symbol: PlayerSymbol? = null,
) {
    /** Indica si la casella encara no ha estat marcada. */
    val isEmpty: Boolean
        get() = symbol == null
}
