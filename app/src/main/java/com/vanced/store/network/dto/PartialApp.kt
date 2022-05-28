package com.vanced.store.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PartialAppDto(
    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("icon")
    val icon: String,

    @SerialName("full")
    val full: String,

    @SerialName("supports_root")
    val supportsRoot: Boolean,

    @SerialName("supports_nonroot")
    val supportsNonroot: Boolean,
)