package com.skfo763.seoul_parking_lot.di.modules

import com.skfo763.data.ParkingLotDataRepository
import com.skfo763.data.executor.JobExecutor
import com.skfo763.domain.ParkingLotRepository
import dagger.Binds
import dagger.Module
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor

@Module
abstract class DataModule {

    @Binds
    abstract fun bindParkingLotDataSource(dataRepository: ParkingLotDataRepository): ParkingLotRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}