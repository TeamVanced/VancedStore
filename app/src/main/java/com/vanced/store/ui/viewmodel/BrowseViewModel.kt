package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.domain.manager.BrowseLayoutMode
import com.vanced.store.domain.manager.PreferenceManager
import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.domain.repository.BrowseRepository
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val browseRepository: BrowseRepository,
    private val preferenceManager: PreferenceManager,
) : ViewModel() {

    sealed class State {
        object Loading : State()
        class Loaded(
            val pinnedApps: List<DomainBrowseApp>,
            val repoApps: List<DomainBrowseApp>,
        ) : State()
        object Error : State()

        val isLoading get() = this is Loading
        val isLoaded get() = this is Loaded
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    fun loadApps() {
        viewModelScope.launch {
            try {
                state = State.Loading
                val pinned = browseRepository.getPinnedApps()
                val repo = browseRepository.getApps()
                state = State.Loaded(
                    pinnedApps = pinned,
                    repoApps = repo
                )
            } catch (e: Exception) {
                state = State.Error
                e.printStackTrace()
            }
        }
    }

    fun switchLayoutMode(newMode: BrowseLayoutMode) {
        preferenceManager.browseLayoutMode = newMode
    }

    init {
        loadApps()
    }
}