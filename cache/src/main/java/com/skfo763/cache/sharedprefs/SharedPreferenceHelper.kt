package com.skfo763.cache.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SharedPreferenceHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "com.skfo763.cache"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
        private const val PREF_KEY_LOCATION_PERMISSION_STATE = "location_permission_state"
    }

    private val bufferPref: SharedPreferences

    init {
        bufferPref = context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = bufferPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = bufferPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

    var isLocationPermissionCheckInRuntime: Boolean
        get() = bufferPref.getBoolean(PREF_KEY_LOCATION_PERMISSION_STATE, true)
        set(state ) = bufferPref.edit().putBoolean(PREF_KEY_LOCATION_PERMISSION_STATE, state).apply()
}