package com.skfo763.presentation.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.domain.interactor.cache.GetCacheBaseInfo
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.presentation.DataSubscriber
import com.skfo763.presentation.mapper.ViewMapper
import com.skfo763.presentation.model.CacheDataModel
import com.skfo763.presentation.resource.Resource
import javax.inject.Inject

class CacheViewModel @Inject internal constructor(
    private val getCacheBaseInfo: GetCacheBaseInfo,
    private val mapper: ViewMapper
): ViewModel() {

    private val _cacheInfo = MutableLiveData<Resource<List<CacheDataModel>>>()
    val cacheInfo: LiveData<Resource<List<CacheDataModel>>> get() = _cacheInfo

    fun getAllCacheInfo() {
        getCacheBaseInfo.execute(
            object: DataSubscriber<CacheDataModel, ParkingLotBaseInfoModel>(_cacheInfo) {
                override val mapFunction: (ParkingLotBaseInfoModel) -> CacheDataModel
                    get() = { mapper.mapBaseModelIntoCacheData(it) }
            }, null)
    }
}