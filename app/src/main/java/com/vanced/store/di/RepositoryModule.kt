package com.vanced.store.di

import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.domain.repository.BrowseRepositoryImpl
import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.RepoService
import org.koin.dsl.module

val repositoryModule = module {
    fun provideBrowseRepository(
        repoService: RepoService,
        githubService: GithubService
    ): BrowseRepository {
        return BrowseRepositoryImpl(
            repositoryService = repoService,
            githubService = githubService
        )
    }

    single { provideBrowseRepository(get(), get()) }
}