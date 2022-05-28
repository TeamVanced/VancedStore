package com.vanced.store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.db.AppDatabase
import com.vanced.store.db.entity.EntityRepo
import com.vanced.store.domain.model.DomainRepo
import com.vanced.store.domain.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoViewModel(
    private val repoRepository: RepoRepository,
    private val database: AppDatabase
) : ViewModel() {

    private val repoDao = database.repoDao()

    sealed class State {
        object Loading : State()
        class Loaded(val repositories: List<DomainRepo>) : State()

        val isLoading get() = this is Loading
        val isLoaded get() = this is Loaded
    }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    fun saveRepo(endpoint: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addRepository(endpoint)
            loadRepositories()
        }
    }

    fun deleteRepo(endpoint: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeRepository(endpoint)
            loadRepositories()
        }
    }

    private suspend fun loadRepositories() {
        _state.value = State.Loading
        val repositories = repoRepository.getRepositories()
        _state.value = State.Loaded(repositories)
    }

    private suspend fun addRepository(endpoint: String) {
        repoDao.insert(
            EntityRepo(endpoint = endpoint)
        )
    }

    private suspend fun removeRepository(endpoint: String) {
        repoDao.deleteByEndpoint(endpoint)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadRepositories()
        }
    }

}