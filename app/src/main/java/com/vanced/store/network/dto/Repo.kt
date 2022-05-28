package com.vanced.store.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoDto(
    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String
)