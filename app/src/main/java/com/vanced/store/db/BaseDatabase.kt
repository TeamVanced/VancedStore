package com.vanced.store.db

import androidx.room.RoomDatabase

abstract class BaseDatabase<T> : RoomDatabase() {

    abstract fun dao(): T

}