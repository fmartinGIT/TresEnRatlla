package com.example.tresenratlla.ui.theme

import androidx.annotation.StringRes
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.tresenratlla.R

/** Colors used to draw each player's symbol. */
@Immutable
data class PlayerColors(
    val x: Color,
    val o: Color,
)

/** Composition-local so cells and status text always use the selected theme's X/O colors. */
val LocalPlayerColors = staticCompositionLocalOf {
    PlayerColors(x = XGreenLight, o = ORedLight)
}

/**
 * The four selectable Material 3 themes.
 *
 * Each entry carries a complete [ColorScheme] plus the explicit colors for X and O.
 * Keeping both light and dark variants in the enum makes persistence and the settings UI simple.
 */
enum class AppTheme(
    @get:StringRes val labelRes: Int,
    val isDark: Boolean,
    val colorScheme: ColorScheme,
    val playerColors: PlayerColors,
) {
    LIGHT_GREEN_RED(
        labelRes = R.string.theme_green_red_light,
        isDark = false,
        colorScheme = lightColorScheme(
            primary = XGreenLight,
            onPrimary = Color.White,
            primaryContainer = GreenRedLightPrimaryContainer,
            onPrimaryContainer = GreenRedLightOnPrimaryContainer,
            secondary = ORedLight,
            onSecondary = Color.White,
            secondaryContainer = GreenRedLightSecondaryContainer,
            onSecondaryContainer = GreenRedLightOnSecondaryContainer,
            tertiary = GreenRedLightTertiary,
            onTertiary = Color.White,
            background = GreenRedLightBackground,
            onBackground = GreenRedLightOnBackground,
            surface = GreenRedLightBackground,
            onSurface = GreenRedLightOnBackground,
            surfaceVariant = GreenRedLightSurfaceVariant,
            onSurfaceVariant = GreenRedLightOnSurfaceVariant,
            outline = GreenRedLightOutline,
        ),
        playerColors = PlayerColors(XGreenLight, ORedLight),
    ),
    DARK_GREEN_RED(
        labelRes = R.string.theme_green_red_dark,
        isDark = true,
        colorScheme = darkColorScheme(
            primary = XGreenDark,
            onPrimary = GreenRedDarkOnPrimary,
            primaryContainer = GreenRedDarkPrimaryContainer,
            onPrimaryContainer = GreenRedDarkOnPrimaryContainer,
            secondary = ORedDark,
            onSecondary = GreenRedDarkOnSecondary,
            secondaryContainer = GreenRedDarkSecondaryContainer,
            onSecondaryContainer = GreenRedDarkOnSecondaryContainer,
            tertiary = GreenRedDarkTertiary,
            onTertiary = GreenRedDarkOnTertiary,
            background = GreenRedDarkBackground,
            onBackground = GreenRedDarkOnBackground,
            surface = GreenRedDarkBackground,
            onSurface = GreenRedDarkOnBackground,
            surfaceVariant = GreenRedDarkSurfaceVariant,
            onSurfaceVariant = GreenRedDarkOnSurfaceVariant,
            outline = GreenRedDarkOutline,
        ),
        playerColors = PlayerColors(XGreenDark, ORedDark),
    ),
    LIGHT_BLUE_ORANGE(
        labelRes = R.string.theme_blue_orange_light,
        isDark = false,
        colorScheme = lightColorScheme(
            primary = XBlueLight,
            onPrimary = Color.White,
            primaryContainer = BlueOrangeLightPrimaryContainer,
            onPrimaryContainer = BlueOrangeLightOnPrimaryContainer,
            secondary = OOrangeLight,
            onSecondary = Color.White,
            secondaryContainer = BlueOrangeLightSecondaryContainer,
            onSecondaryContainer = BlueOrangeLightOnSecondaryContainer,
            tertiary = BlueOrangeLightTertiary,
            onTertiary = Color.White,
            background = BlueOrangeLightBackground,
            onBackground = BlueOrangeLightOnBackground,
            surface = BlueOrangeLightBackground,
            onSurface = BlueOrangeLightOnBackground,
            surfaceVariant = BlueOrangeLightSurfaceVariant,
            onSurfaceVariant = BlueOrangeLightOnSurfaceVariant,
            outline = BlueOrangeLightOutline,
        ),
        playerColors = PlayerColors(XBlueLight, OOrangeLight),
    ),
    DARK_BLUE_ORANGE(
        labelRes = R.string.theme_blue_orange_dark,
        isDark = true,
        colorScheme = darkColorScheme(
            primary = XBlueDark,
            onPrimary = BlueOrangeDarkOnPrimary,
            primaryContainer = BlueOrangeDarkPrimaryContainer,
            onPrimaryContainer = BlueOrangeDarkOnPrimaryContainer,
            secondary = OOrangeDark,
            onSecondary = BlueOrangeDarkOnSecondary,
            secondaryContainer = BlueOrangeDarkSecondaryContainer,
            onSecondaryContainer = BlueOrangeDarkOnSecondaryContainer,
            tertiary = BlueOrangeDarkTertiary,
            onTertiary = BlueOrangeDarkOnTertiary,
            background = BlueOrangeDarkBackground,
            onBackground = BlueOrangeDarkOnBackground,
            surface = BlueOrangeDarkBackground,
            onSurface = BlueOrangeDarkOnBackground,
            surfaceVariant = BlueOrangeDarkSurfaceVariant,
            onSurfaceVariant = BlueOrangeDarkOnSurfaceVariant,
            outline = BlueOrangeDarkOutline,
        ),
        playerColors = PlayerColors(XBlueDark, OOrangeDark),
    ),
}
