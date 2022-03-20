package com.vanced.store.di

import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.viewmodel.MainViewModel
import com.vanced.store.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }

    fun provideBrowseViewModel(
        browseRepository: BrowseRepository
    ): BrowseViewModel {
        return BrowseViewModel(browseRepository)
    }

    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    viewModel { provideMainViewModel() }
    viewModel { provideBrowseViewModel(get()) }
    viewModel { provideSearchViewModel() }
}