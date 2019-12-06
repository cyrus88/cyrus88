package com.datanapps.di.modules

import com.datanapps.userInterface.UserDataActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): UserDataActivity
}