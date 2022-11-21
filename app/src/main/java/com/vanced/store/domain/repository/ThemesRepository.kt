package com.vanced.store.domain.repository

import com.vanced.store.datasource.AppAccent
import com.vanced.store.datasource.AppTheme
import com.vanced.store.datasource.PreferenceDatasource
import kotlinx.coroutines.flow.Flow

interface ThemesRepository {

    fun observeTheme(): Flow<AppTheme>

    suspend fun saveTheme(theme: AppTheme)

    fun observeAccent(): Flow<AppAccent>

    suspend fun saveAccent(accent: AppAccent)
}

class ThemesRepositoryImpl(
    private val preferenceDatasource: PreferenceDatasource
) : ThemesRepository {

    override fun observeTheme(): Flow<AppTheme> {
        return preferenceDatasource.observeAppTheme()
    }

    override suspend fun saveTheme(theme: AppTheme) {
        preferenceDatasource.saveAppTheme(theme)
    }
    
    override fun observeAccent(): Flow<AppAccent> {
        return preferenceDatasource.observeAppAccent()
    }

    override suspend fun saveAccent(accent: AppAccent) {
        preferenceDatasource.saveAppAccent(accent)
    }

}