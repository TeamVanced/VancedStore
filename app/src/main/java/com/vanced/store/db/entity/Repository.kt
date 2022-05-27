package com.vanced.store.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityRepository(
    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey
    @ColumnInfo(name = "endpoint")
    val endpoint: String
)
