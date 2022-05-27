package com.vanced.store.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.vanced.store.db.entity.EntityRepository

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repository")
    suspend fun getAll(): List<EntityRepository>

    @Insert(onConflict = ABORT)
    suspend fun insert(entity: EntityRepository)

    @Query("DELETE FROM repository WHERE endpoint = :endpoint")
    suspend fun deleteByEndpoint(endpoint: String)

    @Delete
    suspend fun delete(entity: EntityRepository)

}