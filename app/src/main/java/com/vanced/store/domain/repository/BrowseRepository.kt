package com.vanced.store.domain.repository

import com.vanced.store.db.VSDatabase
import com.vanced.store.domain.mapper.toBrowseAppModel
import com.vanced.store.domain.mapper.toDomain
import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.network.service.GithubService
import com.vanced.store.network.service.GithubServiceImpl
import com.vanced.store.network.service.RepoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface BrowseRepository {

    suspend fun getPinnedApps(): List<DomainBrowseApp>

    suspend fun getApps(): List<DomainBrowseApp>

}

class BrowseRepositoryImpl(
    private val repoService: RepoService,
    private val githubService: GithubService,
    database: VSDatabase
) : BrowseRepository {

    private val repoDao = database.repoDao()

    override suspend fun getPinnedApps(): List<DomainBrowseApp> {
        return withContext(Dispatchers.IO) {
            val tasks = listOf(
                async {
                    githubService.getRepository(GithubServiceImpl.REPO_MICROG).toBrowseAppModel()
                },
                async {
                    githubService.getRepository(GithubServiceImpl.REPO_STORE).toBrowseAppModel()
                }
            )

            tasks.awaitAll()
        }
    }

    override suspend fun getApps(): List<DomainBrowseApp> {
        return withContext(Dispatchers.IO) {
            val repos = repoDao.getAll()
            val tasks = repos.map { repo ->
                async {
                    repoService.getRepoApps(repo.endpoint).map { it.toDomain() }
                }
            }

            tasks.awaitAll().flatten()
        }
    }

}