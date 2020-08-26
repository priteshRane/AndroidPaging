package com.ransoft.androidpaging.ui.networkanddatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Item
import javax.inject.Inject

class NetworkAndDatabaseViewModel @Inject constructor(
    appDatabase: AppDatabase,
    itemBoundaryCallback: ItemBoundaryCallback
) : ViewModel() {
    private var personsLiveData: LiveData<PagedList<Item>>

    init {
        val factory: DataSource.Factory<Int, Item> =
            appDatabase.itemDao().getAllItemFromDB()

        val pagedListBuilder: LivePagedListBuilder<Int, Item> = LivePagedListBuilder<Int, Item>(
            factory,
            10
        )
        personsLiveData = pagedListBuilder.setBoundaryCallback(itemBoundaryCallback).build()
    }

    fun getPersonsLiveData() = personsLiveData
}