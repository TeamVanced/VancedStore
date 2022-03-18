package com.vanced.store.di

import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.domain.repository.BrowseRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideBrowseRepository(): BrowseRepository {
        return BrowseRepositoryImpl()
    }

    single { provideBrowseRepository() }
}