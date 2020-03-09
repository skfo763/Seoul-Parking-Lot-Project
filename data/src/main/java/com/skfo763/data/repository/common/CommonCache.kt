package com.skfo763.data.repository.common

import com.skfo763.core.PermissionState
import io.reactivex.Single

interface CommonCache {
    fun getLocationPermissionState(): Single<PermissionState.Type>

    fun setLocationPermissionState(state: PermissionState.Type)
}