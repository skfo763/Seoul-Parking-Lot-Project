package com.skfo763.cache.mapper

import com.skfo763.cache.entities.ParkingLotCacheEntity
import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import javax.inject.Inject

class EntityMapperImpl @Inject constructor():
    EntityMapper<ParkingLotBaseInfoEntity, ParkingLotCacheEntity> {

    override fun mappingDataToCache(type: ParkingLotBaseInfoEntity): ParkingLotCacheEntity {
        return ParkingLotCacheEntity(
            type.parkingCode,
            type.name,
            type.address,
            type.parkingType,
            type.parkingTypeName,
            type.operationRule,
            type.operationRuleName,
            type.telNo,
            type.isCharge,
            type.isChargeInName,
            type.groupNumber,
            type.recentSyncTime,
            type.queueStatus,
            type.queueStatusName,
            type.latitude,
            type.longitude
        )
    }

    override fun mappingDataFromCache(type: ParkingLotCacheEntity): ParkingLotBaseInfoEntity {
        return ParkingLotBaseInfoEntity(
            type.parkingCode,
            type.name,
            type.address,
            type.parkingType,
            type.parkingTypeName,
            type.operationRule,
            type.operationRuleName,
            type.telNo,
            type.isCharge,
            type.isChargeInName,
            type.groupNumber,
            type.recentSyncTime,
            type.queueStatus,
            type.queueStatusName,
            type.latitude,
            type.longitude
        )
    }
}