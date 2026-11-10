package com.example.tresenratlla.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tresenratlla.R
import com.example.tresenratlla.model.BoardModel
import com.example.tresenratlla.model.PlayerSymbol
import com.example.tresenratlla.ui.components.BoardGrid
import com.example.tresenratlla.ui.theme.TresEnRatllaTheme
import com.example.tresenratlla.viewmodel.GameViewModel

/**
 * Pantalla principal de la partida.
 *
 * `collectAsState()` converteix el [StateFlow] del ViewModel en un `State`
 * de Compose. Quan el flux emet un tauler nou, Compose recompone els elements
 * que depenen d'aquest estat. La UI només envia accions al ViewModel; no
 * modifica el model directament.
 */
@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel(),
) {
    val board by viewModel.boardState.collectAsState()

    GameContent(
        board = board,
        onCellClick = viewModel::onCellClick,
        onRestartGame = viewModel::restartGame,
    )
}

/**
 * Contingut visual sense dependència del ViewModel.
 *
 * Separar-lo permet previsualitzar la pantalla amb qualsevol estat i testejar
 * la UI de manera senzilla.
 */
@Composable
fun GameContent(
    board: BoardModel,
    onCellClick: (Int) -> Unit,
    onRestartGame: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = stringResource(R.string.game_title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = statusMessage(board),
                style = MaterialTheme.typography.titleLarge,
                color = statusColor(board),
                fontWeight = FontWeight.SemiBold,
            )

            BoardGrid(
                board = board,
                onCellClick = onCellClick,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onRestartGame,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.restart_game))
            }
        }
    }
}

@Composable
private fun statusMessage(board: BoardModel): String {
    val winner = board.winner
    return when {
        winner != null -> stringResource(R.string.winner_message, winner.name)
        board.isDraw -> stringResource(R.string.draw_message)
        else -> stringResource(R.string.turn_message, board.currentTurn.name)
    }
}

@Composable
private fun statusColor(board: BoardModel) = when {
    board.isGameOver -> MaterialTheme.colorScheme.primary
    board.currentTurn == PlayerSymbol.X -> MaterialTheme.colorScheme.primary
    else -> MaterialTheme.colorScheme.tertiary
}

@Preview(showBackground = true)
@Composable
private fun GameContentPreview() {
    TresEnRatllaTheme {
        GameContent(
            board = BoardModel.createInitialBoard(),
            onCellClick = {},
            onRestartGame = {},
        )
    }
}
