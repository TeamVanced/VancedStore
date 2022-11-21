package com.vanced.store.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PreferenceDatasource {

    fun observeAppTheme(): Flow<AppTheme>
    suspend fun saveAppTheme(theme: AppTheme)
    
    fun observeAppAccent(): Flow<AppAccent>
    suspend fun saveAppAccent(accent: AppAccent)

}

class PreferenceDatasourceImpl(
    private val datastore: DataStore<Preferences>
) : PreferenceDatasource {

    override fun observeAppTheme(): Flow<AppTheme> {
        return datastore.data.map {
            AppTheme.values()[it[KEY_APP_THEME] ?: 0]
        }
    }

    override suspend fun saveAppTheme(theme: AppTheme) {
        datastore.edit {
            it[KEY_APP_THEME] = theme.ordinal
        }
    }

    override fun observeAppAccent(): Flow<AppAccent> {
        return datastore.data.map {
            AppAccent.values()[it[KEY_APP_ACCENT] ?: 0]
        }
    }

    override suspend fun saveAppAccent(accent: AppAccent) {
        datastore.edit {
            it[KEY_APP_ACCENT] = accent.ordinal
        }
    }

    private companion object {
        val KEY_APP_THEME = intPreferencesKey("app_theme")
        val KEY_APP_ACCENT = intPreferencesKey("app_accent")
    }

}

enum class AppTheme {
    System, Light, Dark
}

enum class AppAccent {
    Blue, Orange, Pink, Purple
}