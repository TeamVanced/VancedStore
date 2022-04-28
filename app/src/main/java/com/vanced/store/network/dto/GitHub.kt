package com.vanced.store.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositoryDto(
    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String
)

@Serializable
data class GithubReleaseDto(
    @SerialName("name")
    val name: String,

    @SerialName("tag_name")
    val tagName: String,

    @SerialName("assets")
    val url: List<GithubReleaseAssetDto>
)

@Serializable
data class GithubReleaseAssetDto(
    @SerialName("browser_download_url")
    val url: String
)


