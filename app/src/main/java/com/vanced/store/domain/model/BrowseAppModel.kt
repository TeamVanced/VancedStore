package com.vanced.store.domain.model

import com.vanced.store.network.dto.AppDto

data class BrowseAppModel(
    val appName: String,
    val appDescription: String,
    val appIconUrl: String,
    val supportsNonroot: Boolean,
    val supportsRoot: Boolean
)
