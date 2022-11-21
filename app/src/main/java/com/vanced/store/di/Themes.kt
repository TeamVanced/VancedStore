package com.vanced.store.di

import com.vanced.store.domain.repository.ThemesRepository
import com.vanced.store.domain.repository.ThemesRepositoryImpl
import com.vanced.store.ui.viewmodel.ThemesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val themesModule = module {
    singleOf(::ThemesRepositoryImpl) bind ThemesRepository::class
    viewModelOf(::ThemesViewModel)
}