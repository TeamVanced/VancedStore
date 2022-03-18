package com.vanced.store.domain.repository

import com.vanced.store.domain.model.BrowseAppModel

interface BrowseRepository {

    suspend fun getApps(): List<BrowseAppModel>

}

class BrowseRepositoryImpl : BrowseRepository {

    override suspend fun getApps(): List<BrowseAppModel> {
        TODO("Not yet implemented")
    }

}