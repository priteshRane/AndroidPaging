package com.ransoft.androidpaging.ui.networkonly

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.entities.Item
import com.ransoft.androidpaging.data.repositories.ItemRepository
import javax.inject.Inject

class NetworkOnlyViewModel @Inject constructor(
    val itemRepository: ItemRepository,
    val itemDataSource: ItemDataSource
): ViewModel() {
    var itemLiveData  : LiveData<PagedList<Item>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        itemLiveData  = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Item> {

        val dataSourceFactory = object : DataSource.Factory<Int, Item>() {
            override fun create(): DataSource<Int, Item> {
                return itemDataSource
            }
        }
        return LivePagedListBuilder<Int, Item>(dataSourceFactory, config)
    }

    fun getItem():LiveData<PagedList<Item>> = itemLiveData
}