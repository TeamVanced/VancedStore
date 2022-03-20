package com.vanced.store.domain.repository

import com.vanced.store.domain.model.BrowseAppModel

interface BrowseRepository {

    suspend fun getApps(): List<BrowseAppModel>

}

class BrowseRepositoryImpl : BrowseRepository {

    //TODO this is fake lol
    override suspend fun getApps(): List<BrowseAppModel> {
        return buildList {
            repeat(10) {
                add(
                    BrowseAppModel(
                        appName = "Sample",
                        appDescription = "Hi this is an app",
                        appIconUrl = "",
                        supportsNonroot = true,
                        supportsRoot = true
                    )
                )
            }
        }
    }

}