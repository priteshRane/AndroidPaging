package com.ransoft.androidpaging.data.network

import com.ransoft.androidpaging.data.network.responses.PreviousRequestReponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MyApiService @Inject constructor(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi {

    val baseUrl: String = "https://asia-south1-testapis-286008.cloudfunctions.net/ampowerTestApis/"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .build()

    val api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    override suspend fun previousRequestResponse(
        page: Int,
        pageSize: Int
    ): Response<PreviousRequestReponse> {
        return api.previousRequestResponse(page, pageSize)
    }
}