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

    sealed class Screen {
        object Loading : Screen()
        object Browse : Screen()
        object Search : Screen()

        val isLoading get() = this is Loading
        val isBrowse get() = this is Browse
        val isSearch get() = this is Search
    }

    enum class LayoutMode {
        LIST, GRID
    }

    private var apps = listOf<BrowseAppModel>()

    var screen by mutableStateOf<Screen>(Screen.Loading)
        private set

    var layoutMode by mutableStateOf(LayoutMode.LIST)
        private set

    var searchText by mutableStateOf("")
        private set

    val searchApps = mutableStateListOf<BrowseAppModel>()

    val browseApps = mutableStateListOf<BrowseAppModel>()

    fun loadApps() {
        viewModelScope.launch {

        }
    }

    fun switchLayoutMode(newMode: LayoutMode) {
        layoutMode = newMode
    }

    fun search(query: String) {
        searchText = query
        val filteredApps = apps.filter {
            it.appName.contains(query) || it.appDescription.contains(query)
        }
        searchApps.clear()
        searchApps.addAll(filteredApps)
    }

    fun enterSearchScreen() {
        searchApps.addAll(apps)
        screen = Screen.Search
    }

    fun exitSearchScreen() {
        screen = Screen.Browse
        searchApps.clear()
        searchText = ""
    }
}