package com.skfo763.data.source.parkinglot

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import com.skfo763.data.repository.parkinglot.ParkingLotCache
import com.skfo763.data.repository.parkinglot.ParkingLotDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class ParkingLotCacheDataSource
@Inject constructor(private val cache: ParkingLotCache) :
    ParkingLotDataSource {
    override fun clearParkingLotData(): Completable {
        return cache.clearCaches()
    }

    override fun saveParkingLotData(data: ParkingLotBaseInfoEntity): Completable {
        return cache.saveParkingLotInfoToCache(data)
            .doOnComplete {
                cache.setLastCacheTime(System.currentTimeMillis())
            }
    }

    override fun getParkingLotBaseData(): Flowable<List<ParkingLotBaseInfoEntity>> {
        return cache.getAllCaches()
    }

    override fun getParkingLotBaseDataWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        return cache.getCacheDataWithRegion(region)
    }

    override fun getParkingLotBaseDataWithStatus(queueStatus: Boolean): Flowable<List<ParkingLotBaseInfoEntity>> {
        throw UnsupportedOperationException()
    }

    override fun getCacheDataWithChargeInfo(isCharge: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        return cache.getCacheDataWithChargeInfo(isCharge)
    }

    override fun getParkingLotSpecificData(): Flowable<List<ParkingLotSpecificInfoEntity>> {
        throw UnsupportedOperationException()
    }

    override fun getParkingLotSpecificDataWithId(code: String): Flowable<List<ParkingLotSpecificInfoEntity>> {
        throw UnsupportedOperationException()    }

    override fun isCached(): Single<Boolean> {
        return cache.isCached()
    }

}