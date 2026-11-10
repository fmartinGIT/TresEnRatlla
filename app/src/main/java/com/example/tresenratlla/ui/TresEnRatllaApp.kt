package com.example.tresenratlla.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import com.example.tresenratlla.settings.SettingsRepository
import com.example.tresenratlla.ui.settings.SettingsScreen
import com.example.tresenratlla.ui.theme.TresEnRatllaTheme

/**
 * Root composable shared by the game and settings screens.
 *
 * Theme selection is persisted and applied immediately. Language selection uses
 * Android's per-app locale APIs, so the activity may be recreated after a change.
 */
@Composable
fun TresEnRatllaApp(settingsRepository: SettingsRepository) {
    var selectedTheme by remember { mutableStateOf(settingsRepository.theme) }
    var selectedLanguage by remember { mutableStateOf(settingsRepository.language) }
    var showSettings by rememberSaveable { mutableStateOf(false) }

    TresEnRatllaTheme(appTheme = selectedTheme) {
        if (showSettings) {
            SettingsScreen(
                selectedTheme = selectedTheme,
                selectedLanguage = selectedLanguage,
                onThemeSelected = { theme ->
                    selectedTheme = theme
                    settingsRepository.theme = theme
                },
                onLanguageSelected = { language ->
                    selectedLanguage = language
                    settingsRepository.language = language
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(language.tag),
                    )
                },
                onBack = { showSettings = false },
            )
        } else {
            GameScreen(
                onOpenSettings = { showSettings = true },
            )
        }
    }
}
