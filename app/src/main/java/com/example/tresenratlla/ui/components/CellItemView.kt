package com.example.tresenratlla.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tresenratlla.model.CellModel
import com.example.tresenratlla.model.PlayerSymbol

/**
 * Visualització d'una casella individual.
 *
 * El `Surface` amb `onClick` ja aporta l'efecte visual de polsació. La casella
 * només és clicable si la partida continua i està buida; així s'evita enviar
 * moviments invàlids al ViewModel.
 */
@Composable
fun CellItemView(
    cell: CellModel,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val symbolColor = when (cell.symbol) {
        PlayerSymbol.X -> MaterialTheme.colorScheme.primary
        PlayerSymbol.O -> MaterialTheme.colorScheme.tertiary
        null -> Color.Transparent
    }

    Surface(
        onClick = onClick,
        enabled = enabled && cell.isEmpty,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 3.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = cell.symbol?.name.orEmpty(),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = symbolColor,
            )
        }
    }
}
