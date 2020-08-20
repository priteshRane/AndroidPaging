package com.ransoft.androidpaging.data.repositories

import com.ransoft.androidpaging.data.db.AppDatabase
import com.ransoft.androidpaging.data.db.entities.PreviousRequest
import com.ransoft.androidpaging.data.network.MyApi
import com.ransoft.androidpaging.data.network.responses.PreviousRequestReponse
import javax.inject.Inject

class NetworkOnlyRepository @Inject constructor(
    val myApi: MyApi,
    val appDatabase: AppDatabase
) {
    suspend fun previousRequestResponse(page: Int, pageSize: Int): PreviousRequestReponse {
        return myApi.previousRequestResponse(page, pageSize).body()!!
    }

    suspend fun savePreviousRequest(previousRequestsList: List<PreviousRequest>) {
        appDatabase.previousRequestDao().addPreviousRequests(previousRequestsList)
    }

    fun getAllPreviousRequest() = appDatabase.previousRequestDao().getAllPreviousRequests()

    suspend fun deleteAllPreviousRequests() = appDatabase.previousRequestDao().deleteAllPreviousRequests()

    suspend fun getPreviousRequestDataCount() = appDatabase.previousRequestDao().getPreviousRequestCount()
}