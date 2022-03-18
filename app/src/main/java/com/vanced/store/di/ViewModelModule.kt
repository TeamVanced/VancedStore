package com.vanced.store.di

import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.ui.viewmodel.BrowseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    fun provideBrowseViewModel(
        browseRepository: BrowseRepository
    ): BrowseViewModel {
        return BrowseViewModel(browseRepository)
    }

    viewModel { provideBrowseViewModel(get()) }
}