package com.vanced.store.domain.model

data class DomainBrowseApp(
    val appName: String,
    val appDescription: String,
    val appIconUrl: String,
    val supportsNonroot: Boolean,
    val supportsRoot: Boolean
)
