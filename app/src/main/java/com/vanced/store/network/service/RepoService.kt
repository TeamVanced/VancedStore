package com.vanced.store.network.service

import com.vanced.store.network.dto.AppDto
import com.vanced.store.network.dto.PartialAppDto
import com.vanced.store.network.dto.RepoDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RepoService {

    suspend fun getRepo(endpoint: String): RepoDto

    suspend fun getRepoApps(endpoint: String): List<PartialAppDto>

    suspend fun getFullApp(app: String): List<AppDto>

}

class RepoServiceImpl(
    private val client: HttpClient
) : RepoService {

    override suspend fun getRepo(endpoint: String): RepoDto {
        return withContext(Dispatchers.IO) {
            val url = getRepoUrl(endpoint)
            client.get(url).body()
        }
    }

    override suspend fun getRepoApps(endpoint: String): List<PartialAppDto> {
        return withContext(Dispatchers.IO) {
            val url = getRepoAppsUrl(endpoint)
            client.get(url).body()
        }
    }

    override suspend fun getFullApp(app: String): List<AppDto> {
        return withContext(Dispatchers.IO) {
            client.get(app).body()
        }
    }

    companion object {

        fun getRepoUrl(endpoint: String): String {
            return "$endpoint/repo.json"
        }

        fun getRepoAppsUrl(endpoint: String): String {
            return "$endpoint/browse.json"
        }
    }

}