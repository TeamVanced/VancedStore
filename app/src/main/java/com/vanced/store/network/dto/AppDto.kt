package com.vanced.store.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppDto(
    @SerialName("name")
    val appName: String,

    @SerialName("description")
    val appDescription: String,

    @SerialName("data_nonroot")
    val nonrootData: AppNonrootDataDto?,

    @SerialName("data_root")
    val rootData: AppRootDataDto?,
)

@Serializable
data class AppNonrootDataDto(
    @SerialName("version_name")
    override val versionName: String,

    @SerialName("version_code")
    override val versionCode: Int,

    @SerialName("package_name")
    override val packageName: String,
) : AppDataDto

@Serializable
data class AppRootDataDto(
    @SerialName("version_name")
    override val versionName: String,

    @SerialName("version_code")
    override val versionCode: Int,

    @SerialName("package_name")
    override val packageName: String,

    @SerialName("needs_hook")
    val needsHook: Boolean,
) : AppDataDto

private interface AppDataDto {

    val versionName: String

    val versionCode: Int

    val packageName: String

}

