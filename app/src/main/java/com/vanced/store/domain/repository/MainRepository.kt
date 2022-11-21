package com.vanced.store.domain.repository

import com.vanced.store.datasource.AppAccent
import com.vanced.store.datasource.AppTheme
import com.vanced.store.datasource.PreferenceDatasource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun observeTheme(): Flow<AppTheme>

    fun observeAccent(): Flow<AppAccent>

}

class MainRepositoryImpl(
    private val preferenceDatasource: PreferenceDatasource
) : MainRepository {

    override fun observeTheme(): Flow<AppTheme> {
        return preferenceDatasource.observeAppTheme()
    }

    override fun observeAccent(): Flow<AppAccent> {
        return preferenceDatasource.observeAppAccent()
    }

}