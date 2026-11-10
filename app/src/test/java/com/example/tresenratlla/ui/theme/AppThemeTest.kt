package com.example.tresenratlla.ui.theme

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppThemeTest {

    @Test
    fun hasTwoLightAndTwoDarkThemes() {
        assertEquals(2, AppTheme.entries.count { it.isDark })
        assertEquals(2, AppTheme.entries.count { !it.isDark })
    }

    @Test
    fun everyThemeUsesDifferentColoursForXAndO() {
        AppTheme.entries.forEach { theme ->
            assertNotEquals(
                "X and O must be distinguishable in ${theme.name}",
                theme.playerColors.x,
                theme.playerColors.o,
            )
        }
    }

    @Test
    fun everyThemeExposesAnXAndAnOColour() {
        AppTheme.entries.forEach { theme ->
            assertTrue(theme.playerColors.x.alpha > 0f)
            assertTrue(theme.playerColors.o.alpha > 0f)
        }
    }
}
