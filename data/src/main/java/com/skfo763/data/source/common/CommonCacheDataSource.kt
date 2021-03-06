package com.skfo763.data.source.common

import com.skfo763.data.repository.common.CommonCache
import com.skfo763.data.repository.common.CommonDataSource
import io.reactivex.Single
import javax.inject.Inject

class CommonCacheDataSource @Inject constructor (
    private val commonCache: CommonCache
): CommonDataSource {
    override fun isLocationPermissionRuntimeCheck(): Single<Boolean> {
        return commonCache.isLocationPermissionRuntimeCheck()
    }

    override fun setLocationPermissionCheckState(state: Boolean) {
        commonCache.setLocationPermissionCheckState(state)
    }
}