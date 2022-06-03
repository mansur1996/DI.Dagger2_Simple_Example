package com.example.dagger2_example.di.component

import com.example.dagger2_example.activity.MainActivity
import com.example.dagger2_example.di.module.DatabaseModule
import com.example.dagger2_example.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}