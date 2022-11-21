package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanced.store.domain.repository.RepoRepository
import com.vanced.store.ui.screen.ReposState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RepoViewModel(
    private val repoRepository: RepoRepository,
) : ViewModel() {

    var state by mutableStateOf<ReposState>(ReposState.Loading)
        private set

    fun saveRepository(endpoint: String) {
        viewModelScope.launch {
            repoRepository.saveRepository(endpoint)
        }
    }

    fun removeRepository(endpoint: String) {
        viewModelScope.launch {
            repoRepository.removeRepository(endpoint)
        }
    }

    init {
        repoRepository.observeRepositories()
            .onStart {
                state = ReposState.Loading
            }
            .onEach {
                state = ReposState.Success(it)
            }
            .catch {
                state = ReposState.Error
            }
            .launchIn(viewModelScope)
    }

}