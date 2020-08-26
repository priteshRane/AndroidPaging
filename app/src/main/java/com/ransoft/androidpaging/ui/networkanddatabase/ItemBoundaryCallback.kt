package com.ransoft.androidpaging.ui.networkanddatabase

import android.util.Log
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.Item
import com.ransoft.androidpaging.data.repositories.ItemRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import javax.inject.Inject

class ItemBoundaryCallback @Inject constructor(val appDatabase: AppDatabase, val itemRepository: ItemRepository) : PagedList.BoundaryCallback<Item>() {
    private val TAG = "Boundary Callback"
    private val PAGE_SIZE = 10

    override fun onItemAtEndLoaded(itemAtEnd: Item) {
        super.onItemAtEndLoaded(itemAtEnd)

        Log.d(TAG, "Item (Item End): ${itemAtEnd.answer_id}, ${itemAtEnd.question_id}, ${itemAtEnd.content_license}")

        Coroutines.main {
            val itemCount = appDatabase.itemDao().getItemCount()
            Log.d(TAG, "Item (Count): ${itemCount}")
            var page: Int = (itemCount / 10) + 1
            Log.d(TAG, "Item (Page value): $page")
            try {
            val itemResponse =
                itemRepository.itemResponse(page, PAGE_SIZE)
            itemRepository.saveItem(itemResponse.items)
            } catch (e: NoInternetException) {
                Log.d(TAG, "NoInternetException in ItemDataSource: " + e)
            } catch (e: Exception) {
                Log.d(TAG, "Exception in ItemDataSource: " + e.toString())
            }
        }
    }
}