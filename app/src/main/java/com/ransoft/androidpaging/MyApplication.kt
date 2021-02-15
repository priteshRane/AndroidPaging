package com.ransoft.androidpaging

import android.app.Application
import com.ransoft.androidpaging.data.network.MyApiService
import com.ransoft.androidpaging.data.network.NetworkConnectionInterceptor
import com.ransoft.androidpaging.data.repositories.MovieRepository
import com.ransoft.androidpaging.di.component.AppComponent
import com.ransoft.androidpaging.di.component.DaggerAppComponent
import com.ransoft.androidpaging.di.module.RoomModule

open class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            applicationContext,
            RoomModule(this)
        )
    }

    open val networkConnectionInterceptor by lazy {
        NetworkConnectionInterceptor(applicationContext)
    }

    open val appDatabase by lazy {
        RoomModule(this).provideAppDatabase()
    }

    open val networkOnlyRepository by lazy {
        MovieRepository(MyApiService(networkConnectionInterceptor), appDatabase)
    }
}