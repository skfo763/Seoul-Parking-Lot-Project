package com.skfo763.data.source.parkinglot

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import com.skfo763.data.repository.parkinglot.ParkingLotDataSource
import com.skfo763.data.repository.parkinglot.ParkingLotRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class ParkingLotRemoteDataSource
@Inject constructor(private val remote: ParkingLotRemote) :
    ParkingLotDataSource {
    override fun clearParkingLotData(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveParkingLotData(data: ParkingLotBaseInfoEntity): Completable {
        throw UnsupportedOperationException()
    }

    override fun getParkingLotBaseData(): Flowable<List<ParkingLotBaseInfoEntity>> {
        return remote.getParkingLotBaseInfoEntity()
    }

    override fun getParkingLotBaseDataWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        return remote.getBaseInfoWithRegion(region)
    }

    override fun getParkingLotBaseDataWithStatus(queueStatus: Boolean): Flowable<List<ParkingLotBaseInfoEntity>> {
        return remote.getBaseInfoWithHasInfo(queueStatus)
    }

    override fun getCacheDataWithChargeInfo(isCharge: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        throw java.lang.UnsupportedOperationException()
    }

    override fun getParkingLotSpecificData(): Flowable<List<ParkingLotSpecificInfoEntity>> {
        return remote.getParkingLotSpecificInfoEntity()
    }

    override fun getParkingLotSpecificDataWithId(code: String): Flowable<List<ParkingLotSpecificInfoEntity>> {
        return remote.getParkingLotSpecificInfoWithId(code)
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

}