package com.vanced.store.di

import android.content.Context
import com.vanced.store.db.VSDatabase
import com.vanced.store.util.roomDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {

    fun provideAppsRepositoryDatabase(
        context: Context
    ): VSDatabase {
        return roomDatabase(context, databaseName = "app") {
            fallbackToDestructiveMigration()
        }
    }

    singleOf(::provideAppsRepositoryDatabase)
}