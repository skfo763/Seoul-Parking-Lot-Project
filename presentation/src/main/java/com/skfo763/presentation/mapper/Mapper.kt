package com.skfo763.presentation.mapper

import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.model.CacheDataModel
import com.skfo763.presentation.model.HomeDataModel
import com.skfo763.presentation.model.MapDataModel

interface Mapper {
    fun mapBaseModelIntoHomeData(type: ParkingLotBaseInfoModel): HomeDataModel

    fun mapBaseModelIntoMapData(type: ParkingLotBaseInfoModel): MapDataModel

    fun mapBaseModelIntoCacheData(type: ParkingLotBaseInfoModel): CacheDataModel
}