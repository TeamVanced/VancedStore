package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.datasource.AppAccent
import com.vanced.store.datasource.AppTheme
import com.vanced.store.domain.repository.ThemesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ThemesViewModel(
    private val themesRepository: ThemesRepository
) : ViewModel() {

    var theme by mutableStateOf(AppTheme.System)
        private set

    var accent by mutableStateOf(AppAccent.Blue)
        private set

    fun updateTheme(theme: AppTheme) {
        viewModelScope.launch {
            themesRepository.saveTheme(theme)
        }
    }

    fun updateAccent(accent: AppAccent) {
        viewModelScope.launch {
            themesRepository.saveAccent(accent)
        }
    }

    init {
        themesRepository.observeTheme()
            .onEach {
                theme = it
            }.launchIn(viewModelScope)

        themesRepository.observeAccent()
            .onEach {
                accent = it
            }.launchIn(viewModelScope)
    }

}