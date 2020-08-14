package com.ransoft.androidpaging.sampleone.data.network

import com.ransoft.androidpaging.sampleone.data.model.PreviousRequest
import com.ransoft.androidpaging.sampleone.data.network.responses.PreviousRequestReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("previous-request/previousRequestsPagination")
    suspend fun previousRequests(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<PreviousRequestReponse>
}