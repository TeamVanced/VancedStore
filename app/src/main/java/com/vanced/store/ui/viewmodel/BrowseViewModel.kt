package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.domain.manager.BrowseLayoutMode
import com.vanced.store.domain.manager.PreferenceManager
import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.domain.repository.BrowseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val browseRepository: BrowseRepository,
    private val preferenceManager: PreferenceManager,
) : ViewModel() {

    sealed class State {
        object Loading : State()
        class Loaded(val apps: List<BrowseAppModel>) : State()

        val isLoading get() = this is Loading
        val isLoaded get() = this is Loaded
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    var layoutMode by mutableStateOf(preferenceManager.browseLayoutMode)
        private set

    fun loadApps() {
        viewModelScope.launch {
            state = State.Loading
            delay(1000L)
            state = State.Loaded(browseRepository.getApps())
        }
    }

    fun switchLayoutMode(newMode: BrowseLayoutMode) {
        layoutMode = newMode
        preferenceManager.browseLayoutMode = newMode
    }

    init {
        loadApps()
    }
}