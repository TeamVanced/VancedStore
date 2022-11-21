package com.vanced.store.di

import com.vanced.store.domain.repository.LibraryRepository
import com.vanced.store.domain.repository.LibraryRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val libraryModule = module {
    singleOf(::LibraryRepositoryImpl) bind LibraryRepository::class
}