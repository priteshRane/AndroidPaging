package com.ransoft.androidpaging.di.module

import com.ransoft.androidpaging.data.network.MyApi
import com.ransoft.androidpaging.data.network.MyApiService
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideMyApi(myApiService: MyApiService) : MyApi
}