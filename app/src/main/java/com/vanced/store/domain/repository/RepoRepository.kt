package com.vanced.store.domain.repository

import com.vanced.store.db.VSDatabase
import com.vanced.store.db.entity.EntityRepo
import com.vanced.store.domain.mapper.toDomain
import com.vanced.store.domain.model.DomainRepo
import com.vanced.store.network.service.RepoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface RepoRepository {

    fun observeRepositories(): Flow<List<DomainRepo>>

    suspend fun getRepositories(): List<DomainRepo>

    suspend fun saveRepository(endpoint: String)

    suspend fun removeRepository(endpoint: String)

}

class RepoRepositoryImpl(
    private val repoService: RepoService,
    database: VSDatabase
) : RepoRepository {

    private val repoDao = database.repoDao()

    override fun observeRepositories(): Flow<List<DomainRepo>> {
        return repoDao.observeAll().map {
            it.map { repo ->
                repoService.getRepo(repo.endpoint).toDomain(repo.endpoint)
            }
        }
    }

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

    override suspend fun saveRepository(endpoint: String) {
        repoDao.insert(EntityRepo(endpoint))
    }

    override suspend fun removeRepository(endpoint: String) {
        repoDao.deleteByEndpoint(endpoint)
    }

}