package com.skfo763.seoul_parking_lot.di.modules

import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {


    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideParkingLotService {
            return
        }
    }
}