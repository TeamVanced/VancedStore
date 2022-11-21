package com.vanced.store.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.vanced.store.db.entity.EntityRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Query("SELECT * FROM repos")
    fun observeAll(): Flow<List<EntityRepo>>

    @Query("SELECT * FROM repos")
    suspend fun getAll(): List<EntityRepo>

    @Insert(onConflict = ABORT)
    suspend fun insert(entity: EntityRepo)

    @Query("DELETE FROM repos WHERE endpoint = :endpoint")
    suspend fun deleteByEndpoint(endpoint: String)

    @Delete
    suspend fun delete(entity: EntityRepo)

}