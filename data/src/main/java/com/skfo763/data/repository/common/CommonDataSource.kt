package com.skfo763.data.repository.common

import io.reactivex.Single

interface CommonDataSource {
    fun isLocationPermissionRuntimeCheck(): Single<Boolean>

    fun setLocationPermissionCheckState(state: Boolean)
}