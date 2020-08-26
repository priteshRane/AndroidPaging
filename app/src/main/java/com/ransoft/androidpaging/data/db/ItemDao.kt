package com.ransoft.androidpaging.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ransoft.androidpaging.data.db.entities.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(itemEntitiy: List<Item>)

    @Query("SELECT * FROM item")
    fun getAllItem(): LiveData<List<Item>>

    @Query("DELETE FROM item")
    suspend fun deleteAllItem()

    @Query("SELECT COUNT(answerId) FROM item")
    suspend fun getItemCount(): Int

    @Query("SELECT * FROM item")
    fun getAllItemFromDB(): DataSource.Factory<Int, Item>
}