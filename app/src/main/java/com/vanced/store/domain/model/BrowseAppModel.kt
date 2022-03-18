package com.vanced.store.domain.model

import com.vanced.store.network.dto.AppDto

data class BrowseAppModel(
    val appName: String,
    val appDescription: String,
    val appIconUrl: String
) {
    companion object {
        fun fromDto(appDto: AppDto): BrowseAppModel {
            return with (appDto) {
                BrowseAppModel(
                    appName = appName,
                    appDescription = appDescription,
                    appIconUrl = ""
                )
            }
        }
    }
}
