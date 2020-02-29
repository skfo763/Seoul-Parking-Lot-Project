package com.skfo763.remote.mapper

import com.skfo763.data.model.ParkingLotBaseInfoEntity
import com.skfo763.data.model.ParkingLotSpecificInfoEntity
import com.skfo763.remote.entites.ParkingLotEntity

class ParkingLotRemoteMapper :
    RemoteMapper<ParkingLotEntity, ParkingLotBaseInfoEntity, ParkingLotSpecificInfoEntity> {

    override fun mappingToBaseInfo(type: ParkingLotEntity): ParkingLotBaseInfoEntity {
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
            type.recentSyncTime
        )
    }

    override fun mappingToSpecificInfo(type: ParkingLotEntity): ParkingLotSpecificInfoEntity {
        return ParkingLotSpecificInfoEntity(
            type.queueStatus,
            type.queueStatusName,
            type.capacity,
            type.currentParking,
            type.currentParkingUpdateTime,
            type.weekdayBeginTime,
            type.weekdayEndTime,
            type.weekendStartTime,
            type.weekendEndTime,
            type.holidayBeginTime,
            type.holidayEndTime,
            type.isNightFree,
            type.isNightFreeInName,
            type.isSaturdayFree,
            type.isSaturdayFreeInName,
            type.isHolidayFree,
            type.isHolidayFreeInName,
            type.priceInMonthlyFulltime,
            type.price,
            type.pricePerMinute,
            type.additionalPrice,
            type.additionalPricePerMinute,
            type.priceForBus,
            type.pricePerMinuteForBus,
            type.additionalPriceForBus,
            type.additionalPricePerMinuteForBus,
            type.dailyMaximumCharge,
            type.latitude,
            type.longitude,
            type.assignCode,
            type.assignCodeName
        )
    }
}