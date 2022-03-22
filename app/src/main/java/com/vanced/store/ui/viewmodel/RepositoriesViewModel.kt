package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.db.AppsRepositoryDatabase
import com.vanced.store.db.entity.AppsRepositoryEntity
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val repositoryDatabase: AppsRepositoryDatabase
) : ViewModel() {

    sealed class State {
        object Loading : State()
        class Loaded(val repositoryEntities: List<AppsRepositoryEntity>) : State()

        val isLoading get() = this is Loading
        val isLoaded get() = this is Loaded
    }

    var state by mutableStateOf<State>(State.Loading)
        private set

    fun fetch() {
        viewModelScope.launch {
            state = State.Loading
            val repositories = repositoryDatabase.dao().getAll()
            state = State.Loaded(repositories)
        }
    }

    init {
        fetch()
    }

}