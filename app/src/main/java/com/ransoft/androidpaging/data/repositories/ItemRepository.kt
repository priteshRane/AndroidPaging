package com.ransoft.androidpaging.data.repositories

import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Item
import com.ransoft.androidpaging.data.network.MyApi
import com.ransoft.androidpaging.data.network.responses.ItemReponse
import javax.inject.Inject

class ItemRepository @Inject constructor(
    val myApi: MyApi,
    val appDatabase: AppDatabase
) {
    suspend fun itemResponse(page: Int, pageSize: Int): ItemReponse {
        return myApi.itemResponse(page, pageSize, "desc", "activity", "stackoverflow").body()!!
    }

    suspend fun saveItem(itemList: List<Item>) {
        appDatabase.itemDao().addItem(itemList)
    }

    fun getAllItem() = appDatabase.itemDao().getAllItem()

    suspend fun deleteAllItem() = appDatabase.itemDao().deleteAllItem()

    suspend fun getItemDataCount() = appDatabase.itemDao().getItemCount()
}