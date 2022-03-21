package com.vanced.store.util

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified T : RoomDatabase> roomDatabase(
    context: Context,
    databaseName: String,
    builderParams: RoomDatabase.Builder<T>.() -> RoomDatabase.Builder<T> = { this }
): T {
    return Room.databaseBuilder(context, T::class.java, databaseName)
        .builderParams()
        .build()
}