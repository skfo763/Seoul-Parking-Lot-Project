package com.skfo763.seoul_parking_lot.di.modules

import androidx.annotation.UiThread
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.seoul_parking_lot.ui.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @Provides
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}