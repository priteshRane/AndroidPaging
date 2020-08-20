package com.ransoft.androidpaging.di.component

import android.content.Context
import com.ransoft.androidpaging.di.module.NetworkModule
import com.ransoft.androidpaging.di.module.RoomModule
import com.ransoft.androidpaging.ui.databaseonly.DatabaseOnlyFragment
import com.ransoft.androidpaging.ui.networkonly.NetworkOnlyFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, roomModule: RoomModule): AppComponent
    }

    fun inject(networkOnlyFragment: NetworkOnlyFragment)
    fun inject(databaseOnlyFragment: DatabaseOnlyFragment)
}