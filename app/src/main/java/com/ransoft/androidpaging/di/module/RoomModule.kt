package com.ransoft.androidpaging.di.module

import androidx.room.Room
import com.ransoft.androidpaging.MyApplication
import com.ransoft.androidpaging.data.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule(myApplication: MyApplication) {

    var application = myApplication
    lateinit var appDatabase: AppDatabase

    @Provides
    fun provideAppDatabase(): AppDatabase {
        appDatabase = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "AmpowerDatabase.db"
        ).build()
        return appDatabase
    }

    @Provides
    fun providePreviousRequestDao(appDatabase: AppDatabase) = appDatabase.previousRequestDao()
}