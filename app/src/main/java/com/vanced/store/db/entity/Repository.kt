package com.vanced.store.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class EntityRepo(
    @PrimaryKey
    @ColumnInfo(name = "endpoint")
    val endpoint: String
)
