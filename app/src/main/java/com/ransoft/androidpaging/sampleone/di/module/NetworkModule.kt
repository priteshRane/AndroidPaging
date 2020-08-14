package com.ransoft.androidpaging.sampleone.di.module

import com.ransoft.androidpaging.sampleone.data.network.MyApi
import com.ransoft.androidpaging.sampleone.data.network.MyApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideMyApi(myApiService: MyApiService) : MyApi
}