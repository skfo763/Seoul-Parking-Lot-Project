package com.skfo763.seoul_parking_lot.di

import android.app.Application
import com.skfo763.seoul_parking_lot.ParkingLotApplication
import com.skfo763.seoul_parking_lot.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    AppModule::class,
    PresentationModule::class,
    DomainModule::class,
    DataModule::class,
    LocalCacheModule::class,
    RemoteModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: ParkingLotApplication)
}