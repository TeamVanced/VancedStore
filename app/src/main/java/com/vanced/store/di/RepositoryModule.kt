package com.vanced.store.di

import com.vanced.store.db.AppDatabase
import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.domain.repository.BrowseRepositoryImpl
import com.vanced.store.domain.repository.RepoRepository
import com.vanced.store.domain.repository.RepoRepositoryImpl
import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.RepoService
import org.koin.dsl.module

val repositoryModule = module {

    fun provideBrowseRepository(
        repoService: RepoService,
        githubService: GithubService,
        database: AppDatabase,
    ): BrowseRepository {
        return BrowseRepositoryImpl(
            repoService = repoService,
            githubService = githubService,
            database = database
        )
    }

    fun provideRepoRepository(
        repoService: RepoService,
        database: AppDatabase,
    ): RepoRepository {
        return RepoRepositoryImpl(
            repoService = repoService,
            database = database
        )
    }

    single { provideBrowseRepository(get(), get(), get()) }
    single { provideRepoRepository(get(), get()) }
}