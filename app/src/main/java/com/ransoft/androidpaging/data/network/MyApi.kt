package com.ransoft.androidpaging.data.network

import com.ransoft.androidpaging.data.network.responses.ItemReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("2.2/answers")
    suspend fun itemResponse(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String
    ): Response<ItemReponse>
}