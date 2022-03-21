package com.vanced.store.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppsRepositoryDto(
    @SerialName("name")
    val name: String,

    @SerialName("apps")
    val apps: List<PartialAppDto>
)