package com.ransoft.androidpaging.data.repositories

import com.ransoft.androidpaging.data.network.MyApi
import com.ransoft.androidpaging.data.network.responses.PreviousRequestReponse
import javax.inject.Inject

class NetworkOnlyRepository @Inject constructor(
    val myApi: MyApi
) {
    suspend fun previousRequestResponse(page: Int, pageSize: Int): PreviousRequestReponse {
        return myApi.previousRequestResponse(page, pageSize).body()!!
    }
}