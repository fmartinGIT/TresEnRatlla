package com.example.tresenratlla.settings

import android.content.Context
import com.example.tresenratlla.ui.theme.AppTheme

/**
 * Small synchronous settings store backed by SharedPreferences.
 *
 * The app is small enough that DataStore would add unnecessary complexity; these values
 * must be available before the first composition (theme and language).
 */
class SettingsRepository(context: Context) {

    private val preferences = context.applicationContext.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE,
    )

    var theme: AppTheme
        get() = preferences.getString(KEY_THEME, null)
            ?.let { savedName -> AppTheme.entries.firstOrNull { it.name == savedName } }
            ?: DEFAULT_THEME
        set(value) {
            preferences.edit().putString(KEY_THEME, value.name).apply()
        }

    var language: AppLanguage
        get() = AppLanguage.fromTag(preferences.getString(KEY_LANGUAGE, null))
        set(value) {
            preferences.edit().putString(KEY_LANGUAGE, value.tag).apply()
        }

    private companion object {
        const val PREFERENCES_NAME = "tres_en_ratlla_settings"
        const val KEY_THEME = "theme"
        const val KEY_LANGUAGE = "language"
        val DEFAULT_THEME = AppTheme.LIGHT_GREEN_RED
    }
}
