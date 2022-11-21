package com.vanced.store.di

import com.vanced.store.domain.repository.MainRepository
import com.vanced.store.domain.repository.MainRepositoryImpl
import com.vanced.store.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {
    singleOf(::MainRepositoryImpl) bind MainRepository::class
    viewModelOf(::MainViewModel)
}