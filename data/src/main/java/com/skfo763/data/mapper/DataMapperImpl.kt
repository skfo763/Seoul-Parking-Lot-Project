package com.skfo763.data.mapper

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import com.skfo763.domain.model.*
import javax.inject.Inject

class DataMapperImpl @Inject constructor() : DataMapper {
    override fun mapBaseModelToEntity(type: ParkingLotBaseInfoModel): ParkingLotBaseInfoEntity {
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

    override fun mapBaseModelFromEntity(type: ParkingLotBaseInfoEntity): ParkingLotBaseInfoModel {
        return ParkingLotBaseInfoModel(
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

    override fun mapCurrentInfoModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotCurrentInfoModel {
        return ParkingLotCurrentInfoModel(
            type.queueStatus,
            type.queueStatusName,
            type.capacity,
            type.currentParking,
            type.currentParkingUpdateTime
        )
    }

    override fun mapLocationModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotLocationModel {
        return ParkingLotLocationModel(
            type.latitude,
            type.longitude,
            type.assignCode,
            type.assignCodeName
        )
    }

    override fun mapPriceModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotPriceModel {
        return ParkingLotPriceModel(
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
            type.dailyMaximumCharge
        )
    }

    override fun mapTimeInfoModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotTimeInfoModel {
        return ParkingLotTimeInfoModel(
            type.weekdayBeginTime,
            type.weekdayEndTime,
            type.weekendStartTime,
            type.weekendEndTime,
            type.holidayBeginTime,
            type.holidayEndTime
        )
    }

}