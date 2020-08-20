package com.ransoft.androidpaging.ui.networkonly

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.entities.PreviousRequest
import com.ransoft.androidpaging.data.repositories.NetworkOnlyRepository
import javax.inject.Inject

class NetworkOnlyViewModel @Inject constructor(
    val networkOnlyRepository: NetworkOnlyRepository,
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

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, PreviousRequest> {

        val dataSourceFactory = object : DataSource.Factory<Int, PreviousRequest>() {
            override fun create(): DataSource<Int, PreviousRequest> {
                return previousRequestDataSource
            }
        }
        return LivePagedListBuilder<Int, PreviousRequest>(dataSourceFactory, config)
    }

    fun getPreviousRequests():LiveData<PagedList<PreviousRequest>> = previousRequestLiveData
}