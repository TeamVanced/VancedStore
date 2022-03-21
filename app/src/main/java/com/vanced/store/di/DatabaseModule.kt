package com.vanced.store.di

import android.content.Context
import com.vanced.store.db.AppsRepositoryDatabase
import com.vanced.store.util.roomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun provideAppsRepositoryDatabase(
        context: Context
    ): AppsRepositoryDatabase {
        return roomDatabase(context, "app-repositories")
    }

    single { provideAppsRepositoryDatabase(androidContext()) }
}