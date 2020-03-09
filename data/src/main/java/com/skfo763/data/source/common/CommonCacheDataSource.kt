package com.skfo763.data.source.common

import com.skfo763.core.PermissionState
import com.skfo763.data.repository.common.CommonCache
import com.skfo763.data.repository.common.CommonDataSource
import io.reactivex.Single
import javax.inject.Inject

class CommonCacheDataSource @Inject constructor (
    private val commonCache: CommonCache
): CommonDataSource {
    override fun getLocationPermissionState(): Single<PermissionState.Type> {
        return commonCache.getLocationPermissionState()
    }

    override fun setLocationPermissionState(state: PermissionState.Type) {
        commonCache.setLocationPermissionState(state)
    }
}