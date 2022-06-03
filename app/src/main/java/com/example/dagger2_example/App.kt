package com.example.dagger2_example

import android.app.Application
import com.example.dagger2_example.di.component.AppComponent
import com.example.dagger2_example.di.component.DaggerAppComponent
import com.example.dagger2_example.di.module.DatabaseModule

/**
 * Birinchi navbatda App class ishga tushadi undan keyin Activitylar yaratiladi
 */
class App : Application() {

    /**
     * staqtic method/variable
     */
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule(this))
            .build()

    }
}