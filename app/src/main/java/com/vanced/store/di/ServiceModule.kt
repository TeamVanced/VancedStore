package com.vanced.store.di

import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.GithubServiceImpl
import com.vanced.store.network.service.RepoService
import com.vanced.store.network.service.RepoServiceImpl
import io.ktor.client.*
import org.koin.dsl.module

val serviceModule = module {

    fun provideRepositoryService(
        httpClient: HttpClient
    ): RepoService {
        return RepoServiceImpl(
            client = httpClient
        )
    }

    fun provideGithubService(
        httpClient: HttpClient
    ): GithubService {
        return GithubServiceImpl(
            client = httpClient
        )
    }

    single { provideRepositoryService(get()) }
    single { provideGithubService(get()) }
}