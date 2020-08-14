package com.ransoft.androidpaging.sampleone.di.component

import android.content.Context
import com.ransoft.androidpaging.sampleone.di.module.NetworkModule
import com.ransoft.androidpaging.sampleone.ui.SampleOneFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(sampleOneFragment: SampleOneFragment)
}