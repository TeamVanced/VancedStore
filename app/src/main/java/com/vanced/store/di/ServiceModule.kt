package com.vanced.store.di

import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.GithubServiceImpl
import com.vanced.store.network.service.RepositoryService
import com.vanced.store.network.service.RepositoryServiceImpl
import io.ktor.client.*
import org.koin.dsl.module

val serviceModule = module {

    fun provideRepositoryService(
        httpClient: HttpClient
    ): RepositoryService {
        return RepositoryServiceImpl(
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