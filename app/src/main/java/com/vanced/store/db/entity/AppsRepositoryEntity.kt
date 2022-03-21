package com.vanced.store.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps_repository")
data class AppsRepositoryEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "endpoint")
    val endpoint: String
)
