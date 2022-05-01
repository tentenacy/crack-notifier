package com.tenutz.cracknotifier.di.module

import android.app.Activity
import com.tenutz.cracknotifier.application.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {

    @Provides
    fun provideMainActivity(activity: Activity): MainActivity = (activity as MainActivity)

}