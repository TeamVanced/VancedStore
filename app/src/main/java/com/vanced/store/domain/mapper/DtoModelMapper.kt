package com.vanced.store.domain.mapper

import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.domain.model.DomainRepo
import com.vanced.store.network.dto.GithubRepositoryDto
import com.vanced.store.network.dto.PartialAppDto
import com.vanced.store.network.dto.RepoDto

fun GithubRepositoryDto.toBrowseAppModel(): DomainBrowseApp {
    return DomainBrowseApp(
        appName = name,
        appDescription = description,
        appIconUrl = "",
        supportsNonroot = false,
        supportsRoot = false
    )
}

fun PartialAppDto.toDomain(): DomainBrowseApp {
    return DomainBrowseApp(
        appName = name,
        appDescription = description,
        appIconUrl = icon,
        supportsNonroot = supportsNonroot,
        supportsRoot = supportsRoot
    )
}

fun RepoDto.toDomain(endpoint: String): DomainRepo {
    return DomainRepo(
        name = name,
        description = description,
        endpoint = endpoint
    )
}