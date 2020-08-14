package com.ransoft.androidpaging.sampleone.data.repositories

import com.ransoft.androidpaging.sampleone.data.model.PreviousRequest
import com.ransoft.androidpaging.sampleone.data.network.MyApi
import com.ransoft.androidpaging.sampleone.data.network.responses.PreviousRequestReponse
import javax.inject.Inject

class SampleOneRepository @Inject constructor(
    val myApi: MyApi
) {
    suspend fun previousRequest(): PreviousRequestReponse {
        return myApi.previousRequests(1, 10).body()!!
    }
}