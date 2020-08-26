package com.ransoft.androidpaging.ui.networkonly

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ransoft.androidpaging.data.db.entities.Item
import com.ransoft.androidpaging.data.repositories.ItemRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import javax.inject.Inject

class ItemDataSource @Inject constructor(
    val itemRepository: ItemRepository
) : PageKeyedDataSource<Int, Item>() {

    var LOG_TAG_API = "API"
    var PAGE_SIZE = 10
    var FIRST_PAGE = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        Coroutines.main {
            try {
                val itemResponse =
                    itemRepository.itemResponse(FIRST_PAGE, PAGE_SIZE)
                itemRepository.deleteAllItem()
                itemRepository.saveItem(itemResponse.items)
                itemRepository.getAllItem()
                Log.d(LOG_TAG_API, itemRepository.getItemDataCount().toString())
                callback.onResult(itemResponse.items, null, FIRST_PAGE + 1)
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in ItemDataSource: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in ItemDataSource: " + e.toString())
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        Coroutines.main {
            try {
                val itemResponse =
                    itemRepository.itemResponse(params.key, PAGE_SIZE)
                itemRepository.saveItem(itemResponse.items)
                itemRepository.getAllItem()
                Log.d(LOG_TAG_API, itemRepository.getItemDataCount().toString())
                if (itemResponse.has_more!!) {
                    callback.onResult(itemResponse.items, params.key + 1)
                }
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in ItemDataSource: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in ItemDataSource: " + e.toString())
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        Coroutines.main {
            try {
                val itemResponse =
                    itemRepository.itemResponse(params.key, PAGE_SIZE)
                itemRepository.saveItem(itemResponse.items)
                itemRepository.getAllItem()
                Log.d(LOG_TAG_API, itemRepository.getItemDataCount().toString())
                if (params.key > 1) {
                    callback.onResult(itemResponse.items, params.key - 1)
                }
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in ItemDataSource: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in ItemDataSource: " + e.toString())
            }
        }
    }
}