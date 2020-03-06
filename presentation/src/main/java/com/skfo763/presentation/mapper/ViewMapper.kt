package com.skfo763.presentation.mapper

import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.model.CacheDataModel
import com.skfo763.presentation.model.HomeDataModel
import com.skfo763.presentation.model.MapDataModel

class ViewMapper : Mapper {

    override fun mapBaseModelIntoHomeData(type: ParkingLotBaseInfoModel): HomeDataModel {
        return HomeDataModel(
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

    override fun mapBaseModelIntoMapData(type: ParkingLotBaseInfoModel): MapDataModel {
        return MapDataModel(
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

    override fun mapBaseModelIntoCacheData(type: ParkingLotBaseInfoModel): CacheDataModel {
        return CacheDataModel(
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
        )    }
}