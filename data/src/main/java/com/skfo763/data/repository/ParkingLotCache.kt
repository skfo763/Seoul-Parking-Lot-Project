package com.skfo763.data.repository

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ParkingLotCache {

    fun clearCaches(): Completable

    fun saveParkingLotInfoToCache(data: ParkingLotBaseInfoEntity): Completable

    fun getAllCaches(): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getCacheDataWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getCacheDataWithChargeInfo(isCharge: String): Flowable<List<ParkingLotBaseInfoEntity>>

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long)

    fun isExpired(): Boolean
}