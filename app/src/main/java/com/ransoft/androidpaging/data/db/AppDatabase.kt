package com.ransoft.androidpaging.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ransoft.androidpaging.data.db.entities.Movie

@Database(
    entities = [Movie::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}