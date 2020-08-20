package com.ransoft.androidpaging.di.component

import android.content.Context
import com.ransoft.androidpaging.di.module.NetworkModule
import com.ransoft.androidpaging.ui.networkonly.NetworkOnlyFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(networkOnlyFragment: NetworkOnlyFragment)
}