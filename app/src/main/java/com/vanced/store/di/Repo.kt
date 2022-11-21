package com.vanced.store.di

import com.vanced.store.domain.repository.RepoRepository
import com.vanced.store.domain.repository.RepoRepositoryImpl
import com.vanced.store.network.service.RepoService
import com.vanced.store.network.service.RepoServiceImpl
import com.vanced.store.ui.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val reposModule = module {
    singleOf(::RepoServiceImpl) bind RepoService::class
    singleOf(::RepoRepositoryImpl) bind RepoRepository::class
    viewModelOf(::RepoViewModel)
}