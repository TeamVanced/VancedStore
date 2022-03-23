package com.vanced.store.domain.model

import com.vanced.store.db.entity.Repository

data class RepositoryModel(
    val name: String
) {
    companion object {
        fun fromEntity(
            repository: Repository
        ): RepositoryModel {
            return RepositoryModel(
                name = repository.name
            )
        }
    }
}
