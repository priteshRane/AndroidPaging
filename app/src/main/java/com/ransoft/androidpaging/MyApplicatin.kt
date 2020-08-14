package com.ransoft.androidpaging

import android.app.Application
import com.ransoft.androidpaging.sampleone.di.component.AppComponent
import com.ransoft.androidpaging.sampleone.di.component.DaggerAppComponent

class MyApplicatin : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            applicationContext
        )
    }
}