package com.ransoft.androidpaging

import android.app.Application
import com.ransoft.androidpaging.data.network.MyApiService
import com.ransoft.androidpaging.data.network.NetworkConnectionInterceptor
import com.ransoft.androidpaging.data.repositories.NetworkOnlyRepository
import com.ransoft.androidpaging.di.component.AppComponent
import com.ransoft.androidpaging.di.component.DaggerAppComponent

open class MyApplicatin : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            applicationContext
        )
    }

    open val networkConnectionInterceptor by lazy {
        NetworkConnectionInterceptor(applicationContext)
    }

    open val networkOnlyRepository by lazy {
        NetworkOnlyRepository(MyApiService(networkConnectionInterceptor))
    }
}