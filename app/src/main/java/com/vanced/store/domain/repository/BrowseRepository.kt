package com.vanced.store.domain.repository

import com.vanced.store.domain.mapper.toBrowseAppModel
import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.GithubServiceImpl
import com.vanced.store.network.service.RepositoryService

interface BrowseRepository {

    suspend fun getPinnedApps(): List<BrowseAppModel>

    suspend fun getApps(): List<BrowseAppModel>

}

class BrowseRepositoryImpl(
    private val repositoryService: RepositoryService,
    private val githubService: GithubService,
) : BrowseRepository {

    override suspend fun getPinnedApps(): List<BrowseAppModel> {
        val microgDto = githubService.getRepository(GithubServiceImpl.REPO_MICROG)
        val storeDto = githubService.getRepository(GithubServiceImpl.REPO_STORE)
        return listOf(microgDto, storeDto).map {
            it.toBrowseAppModel()
        }
    }

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