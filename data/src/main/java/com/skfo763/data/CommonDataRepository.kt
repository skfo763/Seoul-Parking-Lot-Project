package com.skfo763.data

import com.skfo763.data.source.common.CommonDataSourceFactory
import com.skfo763.domain.CommonRepository
import io.reactivex.Single
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val factory: CommonDataSourceFactory
): CommonRepository {
    override fun getForegroundBarrierState(): Single<Boolean> {
        return factory.getDataSource().isLocationPermissionRuntimeCheck()
    }

    override fun setForegroundBarrierState(state: Boolean) {
        factory.getDataSource().setLocationPermissionCheckState(state)
    }
}