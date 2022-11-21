package com.vanced.store.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.vanced.store.datasource.PreferenceDatasource
import com.vanced.store.datasource.PreferenceDatasourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val Context.datastore by preferencesDataStore("preferences")

val preferenceModule = module {
    single {
        androidContext().datastore
    }
    singleOf(::PreferenceDatasourceImpl) bind PreferenceDatasource::class
}