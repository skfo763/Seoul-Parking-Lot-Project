package com.skfo763.seoul_parking_lot.utils

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class KeyHashUtils {
    companion object {
        // 디버깅용 키 해시
        fun getKeyHash(context: Context, TAG: String) {
            try {
                val info = context.packageManager.getPackageInfo(
                    "com.skfo763.seoul_parking_lot", PackageManager.GET_SIGNATURES)
                info.signatures.forEach {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(it.toByteArray())
                    Log.d(TAG, "Key Hash: ${Base64.encodeToString(md.digest(), Base64.DEFAULT)}")
                }
            } catch (e: NameNotFoundException) {
                e.printStackTrace();
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace();
            }
        }
    }

    // 릴리즈용 키 해시
    /*
    keytool -exportcert alias key0 -keystore <키스토어 경로>\keyStore.jks | openssl sha1 -binary | openssl base64
    위 명령어를 터미널에 쳐서 구합니다.
     */
}