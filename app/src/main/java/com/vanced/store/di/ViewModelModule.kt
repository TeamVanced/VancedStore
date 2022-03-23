package com.vanced.store.di

import com.vanced.store.db.RepositoryDatabase
import com.vanced.store.domain.manager.PreferenceManager
import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.viewmodel.MainViewModel
import com.vanced.store.ui.viewmodel.RepositoriesViewModel
import com.vanced.store.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }

    fun provideBrowseViewModel(
        browseRepository: BrowseRepository,
        preferenceManager: PreferenceManager,
    ): BrowseViewModel {
        return BrowseViewModel(
            browseRepository = browseRepository,
            preferenceManager = preferenceManager
        )
    }

    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    fun provideRepositoriesViewModel(
        repositoriesDatabase: RepositoryDatabase
    ): RepositoriesViewModel {
        return RepositoriesViewModel(
            repositoryDatabase = repositoriesDatabase
        )
    }

    viewModel { provideMainViewModel() }
    viewModel { provideBrowseViewModel(get(), get()) }
    viewModel { provideSearchViewModel() }
    viewModel { provideRepositoriesViewModel(get()) }
}