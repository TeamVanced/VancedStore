package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.domain.repository.BrowseRepository
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val browseRepository: BrowseRepository
) : ViewModel() {

    sealed class State {
        object Loading : State()
        object Browse : State()

        val isLoading get() = this is Loading
        val isBrowse get() = this is Browse
    }

    enum class LayoutMode {
        LIST, GRID
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    var layoutMode by mutableStateOf(LayoutMode.LIST)
        private set

    val apps = mutableStateListOf<BrowseAppModel>()

    fun loadApps() {
        viewModelScope.launch {

        }
    }

    fun switchLayoutMode(newMode: LayoutMode) {
        layoutMode = newMode
    }
}