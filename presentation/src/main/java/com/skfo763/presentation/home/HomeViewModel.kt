package com.skfo763.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.domain.interactor.home.GetAllBaseHomeInfo
import com.skfo763.domain.interactor.home.SearchRegionHomeInfo
import com.skfo763.domain.interactor.home.SearchStatusHomeInfo
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.DataSubscriber
import com.skfo763.presentation.mapper.ViewMapper
import com.skfo763.presentation.model.HomeDataModel
import com.skfo763.presentation.resource.Resource
import com.skfo763.presentation.resource.ResourceState
import javax.inject.Inject

open class HomeViewModel @Inject internal constructor(
    private val getAllBaseInfo: GetAllBaseHomeInfo,
    private val searchRegionInfo: SearchRegionHomeInfo,
    private val searchStatusInfo: SearchStatusHomeInfo,
    private val mapper: ViewMapper
): ViewModel() {

    private val _allBaseInfoLiveData = MutableLiveData<Resource<List<HomeDataModel>>>()
    val allBaseINfoLiveData: LiveData<Resource<List<HomeDataModel>>> get() = _allBaseInfoLiveData

    fun fetchAllBaseInfo() {
        _allBaseInfoLiveData.postValue(Resource(ResourceState.LOADING))
        return getAllBaseInfo.execute(
            object : DataSubscriber<HomeDataModel, ParkingLotBaseInfoModel>(_allBaseInfoLiveData, mapper) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> HomeDataModel
                    get() = { mapper.mapBaseModelIntoHomeData(it) }
            }, null)
    }

    fun searchByRegion(region: String) {
        _allBaseInfoLiveData.postValue(Resource(ResourceState.LOADING))
        return searchRegionInfo.execute(
            object : DataSubscriber<HomeDataModel, ParkingLotBaseInfoModel>(_allBaseInfoLiveData, mapper) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> HomeDataModel
                    get() = { mapper.mapBaseModelIntoHomeData(it) }
            }, region)
    }

    fun searchByStatus(status: Boolean) {
        _allBaseInfoLiveData.postValue(Resource(ResourceState.LOADING))
        return searchStatusInfo.execute(
            object : DataSubscriber<HomeDataModel, ParkingLotBaseInfoModel>(_allBaseInfoLiveData, mapper) {
            override val mapFunction: (ParkingLotBaseInfoModel) -> HomeDataModel
                get() = { mapper.mapBaseModelIntoHomeData(it) }
        }, status)
    }
}