package com.skfo763.presentation.map

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.domain.interactor.map.GetAllBaseMapInfo
import com.skfo763.domain.interactor.map.GetNearestParkingLotInfo
import com.skfo763.domain.interactor.map.HandlePermissionState
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.DataSubscriber
import com.skfo763.presentation.mapper.ViewMapper
import com.skfo763.presentation.model.MapDataModel
import com.skfo763.presentation.resource.Resource
import com.skfo763.presentation.resource.ResourceState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

open class MapViewModel @Inject internal constructor(
    private val getAllBaseMapInfo: GetAllBaseMapInfo,
    private val getNearestParkingLotInfo: GetNearestParkingLotInfo,
    private val handlePermissionState: HandlePermissionState,
    private val mapper: ViewMapper
): ViewModel() {

    private var getPermissionRuntimeAllowed: Boolean = true
    private var currentRegion: String? = null
    val compositeDisposable = CompositeDisposable()

    private val _nearestInfo = MutableLiveData<Resource<List<MapDataModel>>>()
    private val _deniedPermissions = MutableLiveData<List<String>>()
    private val _showBarrier = MutableLiveData<Boolean>()
    private val _permissionGranted = MutableLiveData<Boolean>()
    private val _targetPermissions = MutableLiveData<List<String>>()
    private val _gpsInfo = MutableLiveData<Pair<Double,Double>>()

    val nearestInfo: LiveData<Resource<List<MapDataModel>>> get() = _nearestInfo
    val deniedPermissions: LiveData<List<String>> get() = _deniedPermissions
    val showBarrier: LiveData<Boolean> get() = _showBarrier
    val permissionGranted: LiveData<Boolean> get() = _permissionGranted
    val targetPermissions: LiveData<List<String>> get() = _targetPermissions
    val gpsInfo: LiveData<Pair<Double,Double>> get() = _gpsInfo

    fun getAllBaseMapInfo() {
        getAllBaseMapInfo.execute(
            object: DataSubscriber<MapDataModel, ParkingLotBaseInfoModel>(_nearestInfo) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> MapDataModel
                    get() = { mapper.mapBaseModelIntoMapData(it) }
            }, null)
    }

    fun fetchNearestInfo(region: String) {
        _nearestInfo.postValue(Resource(ResourceState.LOADING, null, null))
        getNearestParkingLotInfo.execute(
            object: DataSubscriber<MapDataModel, ParkingLotBaseInfoModel>(_nearestInfo) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> MapDataModel
                    get() = { mapper.mapBaseModelIntoMapData(it) }
            }, region)
    }

    fun getRegionName(address: String): String? {
        try {
            val addressArray = address.split("\\s".toRegex())
            return if(addressArray.size >= 2 && addressArray[0] == "서울") {
                if(currentRegion == null || currentRegion != addressArray[1])
                    addressArray[1].apply { currentRegion = this }
                else null
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun setMapForeground() {
        handlePermissionState.getBackgroundViewState(Consumer {
            getPermissionRuntimeAllowed = it
            _showBarrier.postValue(it)
        })
    }

    fun setMapForegroundState(state: Boolean) {
        handlePermissionState.setMapForegroundState(state)
    }

    fun requestPermission(view: View, permission: List<String>?) {
        if(!permission.isNullOrEmpty() && getPermissionRuntimeAllowed) {
            _targetPermissions.postValue(permission)
        } else {
            _targetPermissions.postValue(listOf())
        }
    }

    fun setViewStateWithDeniedPermission(deniedPermission: List<String>) {
        setMapForeground()
        _permissionGranted.postValue(false)
        _deniedPermissions.postValue(deniedPermission)
    }

    fun onAllPermissionGranted() {
        setMapForegroundState(false)
        _showBarrier.postValue(false)
        _permissionGranted.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        getAllBaseMapInfo.clearAll()
        getNearestParkingLotInfo.clearAll()
        handlePermissionState.clearAll()
        compositeDisposable.clear()
    }

}