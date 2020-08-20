package com.ransoft.androidpaging.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ransoft.androidpaging.data.db.entities.PreviousRequest

@Dao
interface PreviousRequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPreviousRequests(previousRequestEntitiy: List<PreviousRequest>)

    @Query("SELECT * FROM previousrequest")
    fun getAllPreviousRequests(): LiveData<List<PreviousRequest>>

    @Query("DELETE FROM previousrequest")
    suspend fun deleteAllPreviousRequests()

    @Query("SELECT COUNT(id) FROM previousrequest")
    suspend fun getPreviousRequestCount(): Int
}