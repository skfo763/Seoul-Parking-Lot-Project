package com.skfo763.data.source

import com.skfo763.data.repository.ParkingLotCache
import com.skfo763.data.repository.ParkingLotDataSource
import javax.inject.Inject

class ParkingLotDataSourceFactory
@Inject constructor(private val cache: ParkingLotCache,
                    private val cacheDataSource: ParkingLotCacheDataSource,
                    private val remoteDataSource: ParkingLotRemoteDataSource) {

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