package com.vanced.store.di

import android.content.Context
import androidx.room.migration.Migration
import com.vanced.store.db.RepositoryDatabase
import com.vanced.store.util.roomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun provideAppsRepositoryDatabase(
        context: Context
    ): RepositoryDatabase {
        return roomDatabase(context, "repositories")
    }

    single { provideAppsRepositoryDatabase(androidContext()) }
}