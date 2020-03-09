package com.skfo763.data.repository.parkinglot

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import io.reactivex.Flowable

interface ParkingLotRemote {

    fun getParkingLotBaseInfoEntity(): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getBaseInfoWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getBaseInfoWithHasInfo(hasInfo: Boolean): Flowable<List<ParkingLotBaseInfoEntity>>

    fun getParkingLotSpecificInfoEntity(): Flowable<List<ParkingLotSpecificInfoEntity>>

    fun getParkingLotSpecificInfoWithId(parkingId: String): Flowable<List<ParkingLotSpecificInfoEntity>>
}