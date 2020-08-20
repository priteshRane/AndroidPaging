package com.ransoft.androidpaging.data.repositories

import com.ransoft.androidpaging.data.network.MyApi
import com.ransoft.androidpaging.data.network.responses.PreviousRequestReponse
import javax.inject.Inject

class NetworkOnlyRepository @Inject constructor(
    val myApi: MyApi
) {
    suspend fun previousRequest(): PreviousRequestReponse {
        return myApi.previousRequests(1, 10).body()!!
    }
}