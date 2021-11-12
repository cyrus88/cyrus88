package com.features.di.components


import com.features.MyApplication
import com.features.di.modules.ActivitiesModule
import com.features.di.modules.NetworkModule
import dagger.Component

@Component(
    modules = [
        ActivitiesModule::class, NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(myApplication: MyApplication)
    fun inject (networkModule: NetworkModule)

}