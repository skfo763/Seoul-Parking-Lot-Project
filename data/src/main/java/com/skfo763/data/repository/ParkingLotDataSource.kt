package com.skfo763.data.repository

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ParkingLotDataSource {

    fun clearParkingLotData(): Completable

    fun saveParkingLotData(data: ParkingLotBaseInfoEntity): Completable

    fun getParkingLotBaseData(): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getParkingLotBaseDataWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getParkingLotBaseDataWithStatus(queueStatus: Boolean): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getCacheDataWithChargeInfo(isCharge: String): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getParkingLotSpecificData(): Flowable<List<ParkingLotSpecificInfoEntity>>

    fun getParkingLotSpecificDataWithId(code: String): Flowable<List<ParkingLotSpecificInfoEntity>>

    fun isCached(): Single<Boolean>
}