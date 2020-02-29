package com.skfo763.data.repository

import com.skfo763.data.model.ParkingLotBaseInfoEntity
import com.skfo763.data.model.ParkingLotSpecificInfoEntity
import io.reactivex.Flowable

interface ParkingLotRemote {

    fun getParkingLotBaseInfoEntity(): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getBaseInfoWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getBaseInfoWithHasInfo(hasInfo: Boolean): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getParkingLotSpecificInfoEntity(): Flowable<List<ParkingLotSpecificInfoEntity>>

    fun getParkingLotSpecificInfoWithId(parkingId: String): Flowable<List<ParkingLotSpecificInfoEntity>>
}