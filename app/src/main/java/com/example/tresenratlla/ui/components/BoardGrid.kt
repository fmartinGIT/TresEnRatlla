package com.example.tresenratlla.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tresenratlla.model.BoardModel

/**
 * Tauler de 3x3 construït amb [LazyVerticalGrid].
 *
 * `LazyVerticalGrid` només compon els elements visibles. Aquí el nombre de
 * caselles és petit i fix, però és l'equivalent de `LazyColumn` per a graelles.
 * La graella no s'ha de desplaçar, per això es desactiva el scroll.
 */
@Composable
fun BoardGrid(
    board: BoardModel,
    onCellClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.aspectRatio(1f),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false,
    ) {
        items(
            items = board.cells,
            key = { cell -> cell.id },
        ) { cell ->
            CellItemView(
                cell = cell,
                enabled = !board.isGameOver,
                onClick = { onCellClick(cell.position) },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            )
        }
    }
}
