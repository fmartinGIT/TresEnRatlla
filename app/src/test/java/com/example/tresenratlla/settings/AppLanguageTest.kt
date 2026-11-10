package com.example.tresenratlla.settings

import org.junit.Assert.assertEquals
import org.junit.Test

class AppLanguageTest {

    @Test
    fun fromTag_returnsTheMatchingLanguage() {
        assertEquals(AppLanguage.CATALAN, AppLanguage.fromTag("ca"))
        assertEquals(AppLanguage.SPANISH, AppLanguage.fromTag("es"))
        assertEquals(AppLanguage.ENGLISH, AppLanguage.fromTag("en"))
    }

    @Test
    fun fromTag_fallsBackToCatalanForUnknownValues() {
        assertEquals(AppLanguage.CATALAN, AppLanguage.fromTag(null))
        assertEquals(AppLanguage.CATALAN, AppLanguage.fromTag("fr"))
    }
}
