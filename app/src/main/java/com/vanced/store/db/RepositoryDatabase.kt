package com.vanced.store.db

import androidx.room.Database
import com.vanced.store.db.dao.RepositoryDao
import com.vanced.store.db.entity.Repository

@Database(
    entities = [Repository::class],
    version = 2,
)
abstract class RepositoryDatabase : BaseDatabase<RepositoryDao>()