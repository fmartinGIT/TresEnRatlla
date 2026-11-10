package com.example.tresenratlla.settings

import androidx.annotation.StringRes
import com.example.tresenratlla.R

/**
 * Languages supported by the app. The tag is the value used by Android's per-app
 * locale APIs (AppCompat on API < 33 and the framework on API 33+).
 */
enum class AppLanguage(
    val tag: String,
    @get:StringRes val labelRes: Int,
) {
    CATALAN("ca", R.string.language_catalan),
    SPANISH("es", R.string.language_spanish),
    ENGLISH("en", R.string.language_english);

    companion object {
        val DEFAULT = CATALAN

        /** Resolves a stored tag, falling back to Catalan for unknown or missing values. */
        fun fromTag(tag: String?): AppLanguage =
            entries.firstOrNull { it.tag == tag } ?: DEFAULT
    }
}
