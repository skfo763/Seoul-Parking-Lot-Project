package com.skfo763.data.source.parkinglot

import com.skfo763.data.repository.parkinglot.ParkingLotCache
import com.skfo763.data.repository.parkinglot.ParkingLotDataSource
import javax.inject.Inject

class ParkingLotDataSourceFactory @Inject constructor(
    private val cache: ParkingLotCache,
    private val cacheDataSource: ParkingLotCacheDataSource,
    private val remoteDataSource: ParkingLotRemoteDataSource
) {

    open fun getDataSoruce(isCached:Boolean): ParkingLotDataSource {
        return if(isCached && !cache.isExpired()) {
            cacheDataSource
        } else {
            remoteDataSource
        }
    }

    fun getRemoteDataSource() = remoteDataSource

    fun getCacheDataSource() = cacheDataSource

}