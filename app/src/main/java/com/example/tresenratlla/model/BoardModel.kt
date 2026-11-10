package com.example.tresenratlla.model

/**
 * Estat complet d'una partida de tres en ratlla.
 *
 * Tota la lògica de negoci viu aquí, no al ViewModel ni a la UI. Els mètodes
 * retornen una instància nova amb [copy] en lloc de mutar el tauler actual;
 * aquesta immutabilitat és la base del flux reactiu de MVVM.
 */
data class BoardModel(
    val cells: List<CellModel>,
    val currentTurn: PlayerSymbol,
    val winner: PlayerSymbol? = null,
    val isDraw: Boolean = false,
    val isGameOver: Boolean = false,
) {
    companion object {
        const val BOARD_SIZE = 9

        private val WINNING_COMBINATIONS = listOf(
            // Files
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            // Columnes
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            // Diagonals
            listOf(0, 4, 8),
            listOf(2, 4, 6),
        )

        /**
         * Crea un tauler buit amb nou caselles i el torn iniciat per X.
         * És el punt d'entrada que utilitza el ViewModel en crear-se i en
         * reiniciar la partida.
         */
        fun createInitialBoard(): BoardModel = BoardModel(
            cells = List(BOARD_SIZE) { position ->
                CellModel(id = position, position = position)
            },
            currentTurn = PlayerSymbol.X,
        )
    }

    /**
     * Aplica un moviment a la posició indicada i retorna el tauler resultant.
     *
     * Un moviment s'ignora si el joc ja ha acabat, si la posició no existeix
     * o si la casella ja està ocupada. En cas vàlid es col·loca el símbol del
     * torn actual, es comprova victòria/empat i, si el joc continua, es canvia
     * el torn.
     */
    fun makeMove(position: Int): BoardModel {
        if (isGameOver || position !in cells.indices) return this

        val selectedCell = cells[position]
        if (!selectedCell.isEmpty) return this

        val updatedCells = cells.map { cell ->
            if (cell.position == position) {
                cell.copy(symbol = currentTurn)
            } else {
                cell
            }
        }

        val updatedBoard = copy(cells = updatedCells)
        val winningSymbol = updatedBoard.checkWinner()

        return when {
            winningSymbol != null -> updatedBoard.copy(
                winner = winningSymbol,
                isGameOver = true,
            )

            updatedCells.all { !it.isEmpty } -> updatedBoard.copy(
                isDraw = true,
                isGameOver = true,
            )

            else -> updatedBoard.copy(currentTurn = currentTurn.opposite())
        }
    }

    /**
     * Comprova si hi ha tres símbols iguals en línia.
     *
     * @return el símbol guanyador, o `null` si encara no hi ha guanyador.
     */
    fun checkWinner(): PlayerSymbol? {
        for (combination in WINNING_COMBINATIONS) {
            val firstSymbol = cells[combination.first()].symbol ?: continue
            if (combination.all { position -> cells[position].symbol == firstSymbol }) {
                return firstSymbol
            }
        }
        return null
    }
}
