package com.features

import android.app.Activity
import android.app.Application
import com.features.di.components.DaggerAppComponent
import com.features.di.modules.NetworkModule

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

       // added dagger
          DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .build()
            .inject(this)


    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }


}