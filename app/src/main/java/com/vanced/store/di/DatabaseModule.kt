package com.vanced.store.di

import android.content.Context
import com.vanced.store.db.AppDatabase
import com.vanced.store.util.roomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun provideAppsRepositoryDatabase(
        context: Context
    ): AppDatabase {
        return roomDatabase(context, databaseName = "app") {
            fallbackToDestructiveMigration()
        }
    }

    single { provideAppsRepositoryDatabase(androidContext()) }
}