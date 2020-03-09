package com.skfo763.seoul_parking_lot.di.modules

import com.skfo763.data.repository.parkinglot.ParkingLotRemote
import com.skfo763.remote.services.ParkingLotApi
import com.skfo763.remote.services.ParkingLotService
import com.skfo763.remote.ParkingLotRemoteImpl
import com.skfo763.seoul_parking_lot.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {


    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideParkingLotService(): ParkingLotApi {
            return ParkingLotService.getParkingLotService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindParkingLotRemote(remoteImpl: ParkingLotRemoteImpl): ParkingLotRemote
}