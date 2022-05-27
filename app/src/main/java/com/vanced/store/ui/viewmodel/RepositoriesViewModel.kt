package com.vanced.store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.db.RepositoryDatabase
import com.vanced.store.db.dao.RepositoryDao
import com.vanced.store.db.entity.EntityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    private val repositoryDatabase: RepositoryDatabase
) : ViewModel() {

    sealed class State {
        object Loading : State()
        class Loaded(val repositories: List<EntityRepository>) : State()

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

    fun deleteRepo(entityRepository: EntityRepository) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = repositoryDatabase.dao()
            removeRepository(entityRepository, dao)
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
            EntityRepository(
                name = name,
                endpoint = endpoint
            )
        )
    }

    private suspend fun removeRepository(
        entityRepository: EntityRepository,
        dao: RepositoryDao
    ) {
        dao.delete(entityRepository)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadRepositories(repositoryDatabase.dao())
        }
    }

}