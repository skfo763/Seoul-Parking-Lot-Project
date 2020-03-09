package com.skfo763.data

import com.skfo763.core.PermissionState
import com.skfo763.data.source.common.CommonDataSourceFactory
import com.skfo763.domain.CommonRepository
import io.reactivex.Single
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val factory: CommonDataSourceFactory
): CommonRepository {
    override fun getLocationPermissionState(): Single<PermissionState.Type> {
        return factory.getDataSource().getLocationPermissionState()
    }

    override fun setLocationPermissionState(state: PermissionState.Type) {
        factory.getDataSource().setLocationPermissionState(state)
    }
}