package com.skfo763.seoul_parking_lot.di.modules

import androidx.annotation.UiThread
import com.skfo763.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread
}