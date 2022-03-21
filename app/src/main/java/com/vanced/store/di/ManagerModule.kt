package com.vanced.store.di

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.vanced.store.domain.manager.PreferenceManager
import com.vanced.store.domain.manager.PreferenceManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val managerModule = module {

    fun providePreferenceManager(
        context: Context
    ): PreferenceManager {
        return PreferenceManagerImpl(getDefaultSharedPreferences(context))
    }

    single { providePreferenceManager(androidContext()) }
}