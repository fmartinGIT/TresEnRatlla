package com.example.tresenratlla.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class BoardModelTest {

    @Test
    fun initialBoard_hasNineEmptyCellsAndStartsWithX() {
        val board = BoardModel.createInitialBoard()

        assertEquals(BoardModel.BOARD_SIZE, board.cells.size)
        assertTrue(board.cells.all { it.isEmpty })
        assertEquals(PlayerSymbol.X, board.currentTurn)
        assertNull(board.winner)
        assertFalse(board.isDraw)
        assertFalse(board.isGameOver)
    }

    @Test
    fun makeMove_marksCellAndChangesTurn() {
        val board = BoardModel.createInitialBoard().makeMove(0)

        assertEquals(PlayerSymbol.X, board.cells[0].symbol)
        assertEquals(PlayerSymbol.O, board.currentTurn)
        assertTrue(board.cells.drop(1).all { it.isEmpty })
    }

    @Test
    fun makeMove_ignoresOccupiedAndOutOfRangePositions() {
        val afterFirstMove = BoardModel.createInitialBoard().makeMove(4)

        val afterRepeatedMove = afterFirstMove.makeMove(4)
        val afterInvalidMove = afterFirstMove.makeMove(42)

        assertSame(afterFirstMove, afterRepeatedMove)
        assertSame(afterFirstMove, afterInvalidMove)
        assertEquals(PlayerSymbol.O, afterFirstMove.currentTurn)
    }

    @Test
    fun checkWinner_detectsHorizontalWin() {
        val board = play(0, 3, 1, 4, 2)

        assertEquals(PlayerSymbol.X, board.checkWinner())
        assertEquals(PlayerSymbol.X, board.winner)
        assertTrue(board.isGameOver)
        assertFalse(board.isDraw)
    }

    @Test
    fun checkWinner_detectsVerticalWin() {
        val board = play(0, 1, 3, 2, 6)

        assertEquals(PlayerSymbol.X, board.checkWinner())
        assertEquals(PlayerSymbol.X, board.winner)
        assertTrue(board.isGameOver)
    }

    @Test
    fun checkWinner_detectsDiagonalWin() {
        val board = play(0, 1, 4, 2, 8)

        assertEquals(PlayerSymbol.X, board.checkWinner())
        assertEquals(PlayerSymbol.X, board.winner)
        assertTrue(board.isGameOver)
    }

    @Test
    fun makeMove_endsGameInDrawWhenBoardIsFull() {
        val board = play(0, 1, 2, 4, 3, 5, 7, 6, 8)

        assertNull(board.winner)
        assertTrue(board.isDraw)
        assertTrue(board.isGameOver)
        assertTrue(board.cells.all { !it.isEmpty })
    }

    @Test
    fun makeMove_isIgnoredAfterGameOver() {
        val wonBoard = play(0, 3, 1, 4, 2)

        assertSame(wonBoard, wonBoard.makeMove(8))
    }

    /** Reprodueix una seqüència de moviments alterns començant per X. */
    private fun play(vararg positions: Int): BoardModel =
        positions.fold(BoardModel.createInitialBoard()) { board, position ->
            board.makeMove(position)
        }
}
