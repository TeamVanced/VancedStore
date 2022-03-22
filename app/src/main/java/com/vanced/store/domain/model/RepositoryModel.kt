package com.vanced.store.domain.model

import com.vanced.store.db.entity.AppsRepositoryEntity

data class RepositoryModel(
    val name: String
) {
    companion object {
        fun fromEntity(
            appsRepositoryEntity: AppsRepositoryEntity
        ): RepositoryModel {
            return RepositoryModel(
                name = appsRepositoryEntity.name
            )
        }
    }
}
