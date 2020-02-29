package com.skfo763.remote.entites

import com.google.gson.annotations.SerializedName
import com.skfo763.remote.constants.NetworkErrorCode

data class NetworkResultEntity(
    @SerializedName("CODE") val code: NetworkErrorCode?,
    @SerializedName("MESSAGE") val message: String
)