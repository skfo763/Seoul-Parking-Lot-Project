package com.skfo763.presentation.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.core.PermissionState
import com.skfo763.domain.interactor.map.GetAllBaseMapInfo
import com.skfo763.domain.interactor.map.GetNearestParkingLotInfo
import com.skfo763.domain.interactor.map.HandlePermissionState
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.DataSubscriber
import com.skfo763.presentation.mapper.ViewMapper
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.Resource
import io.reactivex.functions.Consumer
import javax.inject.Inject

open class MapViewModel @Inject internal constructor(
    private val getAllBaseMapInfo: GetAllBaseMapInfo,
    private val getNearestParkingLotInfo: GetNearestParkingLotInfo,
    private val handlePermissionState: HandlePermissionState,
    private val mapper: ViewMapper
): ViewModel() {

    enum class ViewState {

    }

    private val _nearestInfo = MutableLiveData<Resource<List<MapDataModel>>>()
    private val _deniedPermissions = MutableLiveData<List<String>>()
    private val _hideBarrier = MutableLiveData<Boolean>()
    private val _permissionGranted = MutableLiveData<Boolean>()
    private val _targetPermissions = MutableLiveData<List<String>>()

    val nearestInfo: LiveData<Resource<List<MapDataModel>>> get() = _nearestInfo
    val deniedPermissions: LiveData<List<String>> get() = _deniedPermissions
    val hideBarrier: LiveData<Boolean> get() = _hideBarrier
    val permissionGranted: LiveData<Boolean> get() = _permissionGranted
    val targetPermissions: LiveData<List<String>> get() = _targetPermissions

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

    fun setMapForeground() {
        handlePermissionState.getBackgroundViewState(Consumer {
            when(it) {
                PermissionState.Type.DENIED_NEVER_SHOW -> _hideBarrier.postValue(false)
                PermissionState.Type.GRANTED,
                PermissionState.Type.DENIED_SHOW_AGAIN -> _hideBarrier.postValue(true)
                null -> _hideBarrier.postValue(false)
            }
        })
    }

    private fun setPermissionState(state: PermissionState.Type) {
        handlePermissionState.setLocationPermissionState(state)
    }

    fun requestPermission(permission: List<String>?) {
        if(permission.isNullOrEmpty()) return
        _targetPermissions.postValue(permission)
    }

    fun setViewStateWithDeniedPermission(deniedPermission: List<String>) {
        setMapForeground()
        _permissionGranted.postValue(false)
        _deniedPermissions.postValue(deniedPermission)
    }

    fun onAllPermissionGranted() {
        setPermissionState(PermissionState.Type.GRANTED)
        _permissionGranted.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        getAllBaseMapInfo.clearAll()
        getNearestParkingLotInfo.clearAll()
        handlePermissionState.clearAll()
    }
}