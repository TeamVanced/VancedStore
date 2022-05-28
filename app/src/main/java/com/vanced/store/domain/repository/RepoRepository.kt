package com.vanced.store.domain.repository

import com.vanced.store.db.AppDatabase
import com.vanced.store.domain.mapper.toDomain
import com.vanced.store.domain.model.DomainRepo
import com.vanced.store.network.service.RepoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface RepoRepository {

    suspend fun getRepositories(): List<DomainRepo>

}

class RepoRepositoryImpl(
    private val repoService: RepoService,
    private val database: AppDatabase
) : RepoRepository {

    private val repoDao = database.repoDao()

    override suspend fun getRepositories(): List<DomainRepo> {
        return withContext(Dispatchers.IO) {
            val repos = repoDao.getAll()
            val tasks = repos.map { repo ->
                async {
                    repoService.getRepo(repo.endpoint).toDomain(repo.endpoint)
                }
            }

            tasks.awaitAll()
        }
    }

}