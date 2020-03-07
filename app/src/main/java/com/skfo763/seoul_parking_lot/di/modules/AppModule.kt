package com.skfo763.seoul_parking_lot.di.modules

import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.seoul_parking_lot.UiMainThread
import com.skfo763.seoul_parking_lot.ui.cache.CacheFragment
import com.skfo763.seoul_parking_lot.ui.home.HomeFragment
import com.skfo763.seoul_parking_lot.ui.main.MainActivity
import com.skfo763.seoul_parking_lot.ui.map.MapFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiMainThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun contributeCacheFragment(): CacheFragment
}