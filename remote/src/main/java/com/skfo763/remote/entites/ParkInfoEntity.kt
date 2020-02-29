package com.skfo763.remote.entites

import com.google.gson.annotations.SerializedName

data class ParkInfoEntity (
    @SerializedName("list_total_count") val listTotalCount: Int,
    @SerializedName("RESULT") val networkResult: NetworkResultEntity,
    @SerializedName("row") val parkingLotData: List<ParkingLotEntity>
)