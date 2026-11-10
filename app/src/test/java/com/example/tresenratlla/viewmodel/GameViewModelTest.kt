package com.example.tresenratlla.viewmodel

import com.example.tresenratlla.model.PlayerSymbol
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {

    @Test
    fun initialState_isAnEmptyBoardWithXTurn() {
        val viewModel = GameViewModel()

        val state = viewModel.boardState.value
        assertEquals(PlayerSymbol.X, state.currentTurn)
        assertTrue(state.cells.all { it.isEmpty })
        assertFalse(state.isGameOver)
    }

    @Test
    fun onCellClick_updatesTheExposedState() {
        val viewModel = GameViewModel()

        viewModel.onCellClick(4)

        val state = viewModel.boardState.value
        assertEquals(PlayerSymbol.X, state.cells[4].symbol)
        assertEquals(PlayerSymbol.O, state.currentTurn)
    }

    @Test
    fun restartGame_resetsTheBoardAndTheTurn() {
        val viewModel = GameViewModel()
        viewModel.onCellClick(0)
        viewModel.onCellClick(1)

        viewModel.restartGame()

        val state = viewModel.boardState.value
        assertEquals(PlayerSymbol.X, state.currentTurn)
        assertTrue(state.cells.all { it.isEmpty })
        assertFalse(state.isGameOver)
    }
}
