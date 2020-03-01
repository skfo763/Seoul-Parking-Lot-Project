package com.skfo763.domain

import com.skfo763.domain.model.*
import io.reactivex.Completable
import io.reactivex.Flowable

interface ParkingLotRepository {

    fun clearCache(): Completable

    fun saveParkingLotDataIntoCache(data: ParkingLotBaseInfoModel): Completable

    fun loadAllBaseInfoData(): Flowable<List<ParkingLotBaseInfoModel>>

    fun loadBaseInfoWithRegion(region: String): Flowable<List<ParkingLotBaseInfoModel>>

    fun loadBaseInfoWithStatus(status: Boolean): Flowable<List<ParkingLotBaseInfoModel>>

    fun loadCurrentInfoData(id: String): Flowable<List<ParkingLotCurrentInfoModel>>

    fun loadLocationInfo(id: String): Flowable<List<ParkingLotLocationModel>>

    fun loadPriceInfo(id: String): Flowable<List<ParkingLotPriceModel>>

    fun loadTimeInfo(id: String): Flowable<List<ParkingLotTimeInfoModel>>
}