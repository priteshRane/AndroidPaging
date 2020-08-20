package com.ransoft.androidpaging.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ransoft.androidpaging.data.db.entities.PreviousRequest

@Database(
    entities = [PreviousRequest::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun previousRequestDao(): PreviousRequestDao
}