package com.skfo763.seoul_parking_lot.utils

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat

object PermissionProcessor {
    private const val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED

    fun checkPermission(context: Context, vararg permissions: String): List<String> {
        val notGrantedPermission = mutableListOf<String>()
        permissions.forEach {
            if(ActivityCompat.checkSelfPermission(context, it) == PERMISSION_GRANTED) {
                Log.d("PermissionProcessor", "$it : permission is granted")
            } else {
                notGrantedPermission.add(it)
            }
        }
        return notGrantedPermission
    }

    fun checkPermissionResult(permissions: Array<out String>, grantResult: IntArray): List<String> {
        val notGrantedPermission = mutableListOf<String>()
        permissions.forEachIndexed { index, permission ->
            if(grantResult[index] == PERMISSION_GRANTED) {
                Log.d("PermissionProcessor", "$permission : permission is granted")
            } else {
                notGrantedPermission.add(permission)
            }
        }
        return notGrantedPermission
    }
}