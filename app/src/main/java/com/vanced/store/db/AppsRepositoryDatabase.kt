package com.vanced.store.db

import androidx.room.Database
import com.vanced.store.db.dao.AppsRepositoryDao
import com.vanced.store.db.entity.AppsRepositoryEntity

@Database(
    entities = [AppsRepositoryEntity::class],
    version = 1
)
abstract class AppsRepositoryDatabase : BaseDatabase<AppsRepositoryDao>()