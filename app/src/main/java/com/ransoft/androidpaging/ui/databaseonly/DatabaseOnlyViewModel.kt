package com.ransoft.androidpaging.ui.databaseonly

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.PreviousRequest
import javax.inject.Inject

class DatabaseOnlyViewModel @Inject constructor(appDatabase: AppDatabase) : ViewModel() {
    private var personsLiveData: LiveData<PagedList<PreviousRequest>>

    init {
        val factory: DataSource.Factory<Int, PreviousRequest> =
            appDatabase.previousRequestDao().getAllPreviousRequestsFromDB()

        val pagedListBuilder: LivePagedListBuilder<Int, PreviousRequest> = LivePagedListBuilder<Int, PreviousRequest>(factory,
            50)
        personsLiveData = pagedListBuilder.build()
    }

    fun getPersonsLiveData() = personsLiveData
}