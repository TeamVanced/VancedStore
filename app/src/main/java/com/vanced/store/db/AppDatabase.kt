package com.vanced.store.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vanced.store.db.dao.RepoDao
import com.vanced.store.db.entity.EntityRepo

@Database(entities = [EntityRepo::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun repoDao(): RepoDao

}