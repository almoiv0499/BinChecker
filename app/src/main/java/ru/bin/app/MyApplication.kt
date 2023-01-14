package ru.bin.app

import android.app.Application
import ru.bin.di.component.AppComponent
import ru.bin.di.component.DaggerAppComponent

class MyApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }

}