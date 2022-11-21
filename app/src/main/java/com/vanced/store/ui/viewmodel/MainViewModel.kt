package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.datasource.AppAccent
import com.vanced.store.datasource.AppTheme
import com.vanced.store.domain.repository.MainRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    var appTheme by mutableStateOf(AppTheme.System)
        private set

    var appAccent by mutableStateOf(AppAccent.Blue)
        private set

    init {
        mainRepository.observeTheme()
            .onEach { appTheme = it }
            .launchIn(viewModelScope)

        mainRepository.observeAccent()
            .onEach { appAccent = it }
            .launchIn(viewModelScope)
    }

}