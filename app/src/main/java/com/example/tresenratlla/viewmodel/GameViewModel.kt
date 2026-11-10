package com.example.tresenratlla.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tresenratlla.model.BoardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel que connecta el model del joc amb la interfície Compose.
 *
 * La font de veritat és un [MutableStateFlow] privat. La UI no pot modificar-lo
 * directament: només pot cridar els mètodes públics [onCellClick] i
 * [restartGame], i observar l'estat de només lectura que exposa [boardState].
 */
class GameViewModel : ViewModel() {

    private val _boardState = MutableStateFlow(BoardModel.createInitialBoard())

    /**
     * Estat observable de només lectura.
     *
     * `asStateFlow()` retorna una vista immutable del flux: evita que la UI
     * assigni valors directament i obliga que els canvis passin pels mètodes
     * del ViewModel.
     */
    val boardState: StateFlow<BoardModel> = _boardState.asStateFlow()

    /**
     * Processa el clic d'una casella. Si el moviment no és vàlid, el model
     * retorna el mateix tauler i no es produeix cap canvi d'estat.
     */
    fun onCellClick(position: Int) {
        _boardState.value = _boardState.value.makeMove(position)
    }

    /** Reinicia la partida amb un tauler buit i el torn de X. */
    fun restartGame() {
        _boardState.value = BoardModel.createInitialBoard()
    }
}
