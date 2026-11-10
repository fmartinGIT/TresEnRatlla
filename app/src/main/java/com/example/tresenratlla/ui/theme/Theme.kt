package com.example.tresenratlla.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Applies the selected Material 3 color scheme and exposes the matching X/O colors
 * through [LocalPlayerColors].
 */
@Composable
fun TresEnRatllaTheme(
    appTheme: AppTheme = AppTheme.LIGHT_GREEN_RED,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalPlayerColors provides appTheme.playerColors) {
        MaterialTheme(
            colorScheme = appTheme.colorScheme,
            typography = Typography,
            content = content,
        )
    }
}
