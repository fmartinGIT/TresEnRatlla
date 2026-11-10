package com.example.tresenratlla.model

/**
 * Símbol que pot ocupar una casella. Els valors són exactament els que es
 * mostraran a la interfície: "X" i "O".
 */
enum class PlayerSymbol {
    X,
    O;

    /** Retorna el símbol del jugador que ha de jugar al torn següent. */
    fun opposite(): PlayerSymbol = if (this == X) O else X
}
