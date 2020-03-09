package com.skfo763.cache

import com.skfo763.cache.sharedprefs.SharedPreferenceHelper
import com.skfo763.data.repository.common.CommonCache
import io.reactivex.Single
import javax.inject.Inject

class CommonCacheImpl @Inject constructor(
    val db: ParkingLotDatabase,
    private val preferenceHelper: SharedPreferenceHelper
) : CommonCache {
    override fun isLocationPermissionRuntimeCheck(): Single<Boolean> {
        return Single.create {
            it.onSuccess(preferenceHelper.isLocationPermissionCheckInRuntime)
        }
    }

    override fun setLocationPermissionCheckState(state: Boolean) {
        preferenceHelper.isLocationPermissionCheckInRuntime = state
    }
}
