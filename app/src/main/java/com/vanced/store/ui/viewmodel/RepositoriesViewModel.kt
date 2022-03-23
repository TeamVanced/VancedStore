package com.vanced.store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.db.RepositoryDatabase
import com.vanced.store.db.dao.RepositoryDao
import com.vanced.store.db.entity.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val repositoryDatabase: RepositoryDatabase
) : ViewModel() {

    sealed class State {
        object Loading : State()
        class Loaded(val repositories: List<Repository>) : State()

        val isLoading get() = this is Loading
        val isLoaded get() = this is Loaded
    }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    fun saveRepo(name: String, endpoint: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = repositoryDatabase.dao()
            addRepository(name, endpoint, dao)
            loadRepositories(dao)
        }
    }

    fun deleteRepo(repository: Repository) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = repositoryDatabase.dao()
            removeRepository(repository, dao)
            loadRepositories(dao)
        }
    }

    private suspend fun loadRepositories(dao: RepositoryDao) {
        _state.value = State.Loading
        val repositories = dao.getAll()
        _state.value = State.Loaded(repositories)
    }

    private suspend fun addRepository(
        name: String,
        endpoint: String,
        dao: RepositoryDao
    ) {
        dao.insert(
            Repository(
                name = name,
                endpoint = endpoint
            )
        )
    }

    private suspend fun removeRepository(
        repository: Repository,
        dao: RepositoryDao
    ) {
        dao.delete(repository)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadRepositories(repositoryDatabase.dao())
        }
    }

}