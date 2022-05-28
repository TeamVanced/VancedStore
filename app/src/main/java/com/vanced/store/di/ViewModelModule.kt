package com.vanced.store.di

import com.vanced.store.db.AppDatabase
import com.vanced.store.domain.manager.PreferenceManager
import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.domain.repository.RepoRepository
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.viewmodel.MainViewModel
import com.vanced.store.ui.viewmodel.RepoViewModel
import com.vanced.store.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideMainViewModel(
        preferenceManager: PreferenceManager
    ): MainViewModel {
        return MainViewModel(
            preferenceManager = preferenceManager
        )
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
        repoRepository: RepoRepository,
        database: AppDatabase,
    ): RepoViewModel {
        return RepoViewModel(
            repoRepository = repoRepository,
            database = database
        )
    }

    viewModel { provideMainViewModel(get()) }
    viewModel { provideBrowseViewModel(get(), get()) }
    viewModel { provideSearchViewModel() }
    viewModel { provideRepositoriesViewModel(get(), get()) }
}