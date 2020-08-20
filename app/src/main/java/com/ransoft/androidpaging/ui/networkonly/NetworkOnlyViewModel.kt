package com.ransoft.androidpaging.ui.networkonly

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.model.PreviousRequest
import com.ransoft.androidpaging.data.repositories.NetworkOnlyRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import javax.inject.Inject

class NetworkOnlyViewModel @Inject constructor(
    val previousRequestDataSource: PreviousRequestDataSource
): ViewModel() {
    var previousRequestLiveData  : LiveData<PagedList<PreviousRequest>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        previousRequestLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPreviousRequests():LiveData<PagedList<PreviousRequest>> = previousRequestLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, PreviousRequest> {

        val dataSourceFactory = object : DataSource.Factory<Int, PreviousRequest>() {
            override fun create(): DataSource<Int, PreviousRequest> {
                return previousRequestDataSource
            }
        }
        return LivePagedListBuilder<Int, PreviousRequest>(dataSourceFactory, config)
    }
}