package com.ransoft.androidpaging.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ransoft.androidpaging.data.db.entities.Item

@Database(
    entities = [Item::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}