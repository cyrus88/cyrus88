package com.datanapps.di.components


import com.datanapps.MyApplication
import com.datanapps.di.modules.ActivitiesModule
import com.datanapps.di.modules.NetworkModule
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