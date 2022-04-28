package com.vanced.store.domain.mapper

import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.network.dto.GithubRepositoryDto

fun GithubRepositoryDto.toBrowseAppModel(): BrowseAppModel {
    return BrowseAppModel(
        appName = name,
        appDescription = description,
        appIconUrl = "",
        supportsNonroot = false,
        supportsRoot = false
    )
}