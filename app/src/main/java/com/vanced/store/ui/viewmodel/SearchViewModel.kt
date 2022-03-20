package com.vanced.store.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    var searchValue by mutableStateOf("")
        private set

    fun search(query: String) {
        searchValue = query
    }

}