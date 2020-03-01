package com.skfo763.data.mapper

import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.entities.ParkingLotSpecificInfoEntity
import com.skfo763.domain.model.*

interface DataMapper {

    fun mapBaseModelToEntity(type: ParkingLotBaseInfoModel): ParkingLotBaseInfoEntity

    fun mapBaseModelFromEntity(type: ParkingLotBaseInfoEntity): ParkingLotBaseInfoModel

    fun mapCurrentInfoModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotCurrentInfoModel

    fun mapLocationModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotLocationModel

    fun mapPriceModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotPriceModel

    fun mapTimeInfoModelFromEntity(type: ParkingLotSpecificInfoEntity): ParkingLotTimeInfoModel

}