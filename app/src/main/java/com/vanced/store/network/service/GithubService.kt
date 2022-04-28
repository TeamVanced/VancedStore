package com.vanced.store.network.service

import com.vanced.store.network.dto.GithubReleaseDto
import com.vanced.store.network.dto.GithubRepositoryDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GithubService {

    suspend fun getRepository(repo: String): GithubRepositoryDto

    suspend fun getRepositoryLatestRelease(repo: String): GithubReleaseDto

}

class GithubServiceImpl(
    private val client: HttpClient
) : GithubService {

    override suspend fun getRepository(repo: String): GithubRepositoryDto {
        return withContext(Dispatchers.IO) {
            client.get(getGithubRepoApiUrl(repo)).body()
        }
    }

    override suspend fun getRepositoryLatestRelease(repo: String): GithubReleaseDto {
        return withContext(Dispatchers.IO) {
            client.get(getGithubRepoLatestReleaseApiUrl(repo)).body()
        }
    }

    companion object {
        const val GITHUB_API = "https://api.github.com"

        const val REPO_MICROG = "VancedMicrog"
        const val REPO_STORE = "VancedStore"

        fun getGithubRepoApiUrl(repo: String): String {
            return "$GITHUB_API/repos/TeamVanced/$repo"
        }

        fun getGithubRepoLatestReleaseApiUrl(repo: String): String {
            val repoUrl = getGithubRepoApiUrl(repo)
            return "$repoUrl/releases/latest"
        }
    }

}