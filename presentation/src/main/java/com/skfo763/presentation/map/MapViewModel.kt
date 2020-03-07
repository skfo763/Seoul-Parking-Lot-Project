package com.skfo763.presentation.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.domain.interactor.map.GetAllBaseMapInfo
import com.skfo763.domain.interactor.map.GetNearestParkingLotInfo
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.DataSubscriber
import com.skfo763.presentation.mapper.ViewMapper
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.Resource
import javax.inject.Inject

open class MapViewModel @Inject internal constructor(
    private val getAllBaseMapInfo: GetAllBaseMapInfo,
    private val getNearestParkingLotInfo: GetNearestParkingLotInfo,
    private val mapper: ViewMapper
): ViewModel() {

    private val _nearestInfo = MutableLiveData<Resource<List<MapDataModel>>>()
    val nearestInfo: LiveData<Resource<List<MapDataModel>>> get() = _nearestInfo

    fun getAllBaseMapInfo() {
        getAllBaseMapInfo.execute(
            object: DataSubscriber<MapDataModel, ParkingLotBaseInfoModel>(_nearestInfo) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> MapDataModel
                    get() = { mapper.mapBaseModelIntoMapData(it) }
            }, null)
    }

    fun fetchNearestInfo(region: String) {
        getNearestParkingLotInfo.execute(
            object: DataSubscriber<MapDataModel, ParkingLotBaseInfoModel>(_nearestInfo) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> MapDataModel
                    get() = { mapper.mapBaseModelIntoMapData(it) }
            }, region)
    }
}