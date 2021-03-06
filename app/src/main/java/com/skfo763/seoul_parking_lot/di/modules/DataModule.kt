package com.skfo763.seoul_parking_lot.di.modules

import com.skfo763.data.CommonDataRepository
import com.skfo763.data.ParkingLotDataRepository
import com.skfo763.data.executor.JobExecutor
import com.skfo763.domain.CommonRepository
import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.ThreadExecutor
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindParkingLotDataRepository(dataRepository: ParkingLotDataRepository): ParkingLotRepository

    @Binds
    abstract fun bindCommonDataRepository(dataRepository: CommonDataRepository): CommonRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}