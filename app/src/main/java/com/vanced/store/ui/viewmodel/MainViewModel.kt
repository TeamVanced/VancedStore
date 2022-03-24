package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.vanced.store.domain.manager.ApplicationAccent
import com.vanced.store.domain.manager.ApplicationTheme
import com.vanced.store.domain.manager.PreferenceManager

class MainViewModel(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    var applicationTheme by mutableStateOf(preferenceManager.applicationTheme)
        private set

    var applicationAccent by mutableStateOf(preferenceManager.applicationAccent)
        private set

    fun updateTheme(theme: ApplicationTheme) {
        applicationTheme = theme
        preferenceManager.applicationTheme = theme
    }

    fun updateAccent(accent: ApplicationAccent) {
        applicationAccent = accent
        preferenceManager.applicationAccent = accent
    }

}