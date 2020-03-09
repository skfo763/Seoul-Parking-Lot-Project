package com.skfo763.domain

import com.skfo763.core.PermissionState
import io.reactivex.Single

interface CommonRepository {
    fun getLocationPermissionState(): Single<PermissionState.Type>

    fun setLocationPermissionState(state: PermissionState.Type)
}