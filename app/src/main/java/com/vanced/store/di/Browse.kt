package com.vanced.store.di

import com.vanced.store.domain.repository.BrowseRepository
import com.vanced.store.domain.repository.BrowseRepositoryImpl
import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.GithubServiceImpl
import com.vanced.store.ui.viewmodel.BrowseViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val browseModule = module {
    singleOf(::GithubServiceImpl) bind GithubService::class
    singleOf(::BrowseRepositoryImpl) bind BrowseRepository::class
    viewModelOf(::BrowseViewModel)
}