package com.ransoft.androidpaging.sampleone.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.ransoft.androidpaging.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    context: Context
) : Interceptor {

    private val applicationContext = context

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data connection")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwrk = connectivityManager.activeNetworkInfo
        return activeNetwrk?.isConnectedOrConnecting == true
    }
}