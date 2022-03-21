package com.vanced.store.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.vanced.store.db.entity.AppsRepositoryEntity

@Dao
interface AppsRepositoryDao {

    @Query("SELECT * FROM apps_repository")
    suspend fun getAll(): List<AppsRepositoryEntity>

    @Delete
    suspend fun delete(entity: AppsRepositoryEntity)

}