package com.skfo763.seoul_parking_lot.di.modules

import android.app.Application
import androidx.room.Room
import com.skfo763.cache.CommonCacheImpl
import com.skfo763.cache.DBConstants
import com.skfo763.cache.ParkingLotCacheImpl
import com.skfo763.cache.ParkingLotDatabase
import com.skfo763.data.repository.common.CommonCache
import com.skfo763.data.repository.parkinglot.ParkingLotCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LocalCacheModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideParkingLotDatabase(application: Application): ParkingLotDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                ParkingLotDatabase::class.java,
                DBConstants.DatabaseName
            ).build()
        }
    }

    @Binds
    abstract fun bindParkingLotCache(cacheImpl: ParkingLotCacheImpl): ParkingLotCache

    @Binds
    abstract fun bindCommonCache(cacheImpl: CommonCacheImpl): CommonCache
}