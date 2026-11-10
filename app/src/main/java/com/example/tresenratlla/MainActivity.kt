package com.example.tresenratlla

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.tresenratlla.settings.SettingsRepository
import com.example.tresenratlla.ui.TresEnRatllaApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsRepository = SettingsRepository(this)
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(settingsRepository.language.tag),
        )

        enableEdgeToEdge()
        setContent {
            TresEnRatllaApp(settingsRepository = settingsRepository)
        }
    }
}
