package com.skfo763.remote

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import com.skfo763.data.repository.ParkingLotRemote
import com.skfo763.remote.constants.NetworkErrorCode
import com.skfo763.remote.entites.ParkingLotEntity
import com.skfo763.remote.mapper.ParkingLotRemoteMapper
import com.skfo763.remote.services.ParkingLotApi
import io.reactivex.Flowable
import javax.inject.Inject

class ParkingLotRemoteImpl
@Inject constructor(private val api: ParkingLotApi,
                    private val mapper: ParkingLotRemoteMapper
): ParkingLotRemote {

    private val mappingToBase: (ParkingLotEntity) -> (ParkingLotBaseInfoEntity) = {
        mapper.mappingToBaseInfo(it)
    }

    private val mappingToSpecific: (ParkingLotEntity) -> (ParkingLotSpecificInfoEntity) = {
        mapper.mappingToSpecificInfo(it)
    }

    override fun getParkingLotBaseInfoEntity(): Flowable<List<ParkingLotBaseInfoEntity>> {
        return api.getParkingLotData()
            .flatMap { Flowable.just(it.wrapperData) }
            .flatMap <List<ParkingLotBaseInfoEntity>> {
                val networkResult = it.networkResult
                if(networkResult.code == NetworkErrorCode.NORMAL) {
                    Flowable.just(it.parkingLotData.map(mappingToBase))
                } else {
                    Flowable.error(Throwable(networkResult.message))
                }
            }
    }

    override fun getBaseInfoWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        return api.getParkingLotData(region = region)
            .flatMap { Flowable.just(it.wrapperData) }
            .flatMap <List<ParkingLotBaseInfoEntity>> {
                val networkResult = it.networkResult
                if(networkResult.code == NetworkErrorCode.NORMAL) {
                    Flowable.just(it.parkingLotData.map(mappingToBase))
                } else {
                    Flowable.error(Throwable(networkResult.message))
                }
            }
    }

    override fun getBaseInfoWithHasInfo(hasInfo: Boolean): Flowable<List<ParkingLotBaseInfoEntity>> {
        return api.getParkingLotData(parkingCode = hasInfo.toString())
            .flatMap { Flowable.just(it.wrapperData) }
            .flatMap <List<ParkingLotBaseInfoEntity>> {
                val networkResult = it.networkResult
                if(networkResult.code == NetworkErrorCode.NORMAL) {
                    Flowable.just(it.parkingLotData.map(mappingToBase))
                } else {
                    Flowable.error(Throwable(networkResult.message))
                }
            }
    }

    override fun getParkingLotSpecificInfoEntity(): Flowable<List<ParkingLotSpecificInfoEntity>> {
        return api.getParkingLotData()
            .flatMap { Flowable.just(it.wrapperData) }
            .flatMap <List<ParkingLotSpecificInfoEntity>> {
                val networkResult = it.networkResult
                if(networkResult.code == NetworkErrorCode.NORMAL) {
                    Flowable.just(it.parkingLotData.map(mappingToSpecific))
                } else {
                    Flowable.error(Throwable(networkResult.message))
                }
            }
    }

    override fun getParkingLotSpecificInfoWithId(parkingId: String): Flowable<List<ParkingLotSpecificInfoEntity>> {
        return api.getParkingLotData(parkingCode = parkingId)
            .flatMap { Flowable.just(it.wrapperData) }
            .flatMap <List<ParkingLotSpecificInfoEntity>> {
                val networkResult = it.networkResult
                if(networkResult.code == NetworkErrorCode.NORMAL) {
                    Flowable.just(it.parkingLotData.map(mappingToSpecific))
                } else {
                    Flowable.error(Throwable(networkResult.message))
                }
            }
    }
}