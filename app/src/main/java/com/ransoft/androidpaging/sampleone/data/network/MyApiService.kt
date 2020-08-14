package com.ransoft.androidpaging.sampleone.data.network

import com.ransoft.androidpaging.sampleone.data.model.PreviousRequest
import com.ransoft.androidpaging.sampleone.data.network.responses.PreviousRequestReponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import javax.inject.Inject

class MyApiService @Inject constructor(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi {

    val baseUrl: String = "http://192.168.42.251:5000/"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .build()

    val api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    override suspend fun previousRequests(
        page: Int,
        pageSize: Int
    ): Response<PreviousRequestReponse> {
        return api.previousRequests(page, pageSize)
    }
}