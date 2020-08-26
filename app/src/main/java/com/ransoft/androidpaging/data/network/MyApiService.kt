package com.ransoft.androidpaging.data.network

import com.ransoft.androidpaging.data.network.responses.ItemReponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MyApiService @Inject constructor(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi {
    val baseUrl: String = "https://api.stackexchange.com/"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .build()

    val api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    override suspend fun itemResponse(
        page: Int,
        pagesize: Int,
        order: String,
        sort: String,
        site: String
    ): Response<ItemReponse> {
        return api.itemResponse(page, pagesize, order, sort, site)
    }
}