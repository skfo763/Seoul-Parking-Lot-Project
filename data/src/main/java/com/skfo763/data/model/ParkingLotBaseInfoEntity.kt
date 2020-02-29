package com.skfo763.data.model

data class ParkingLotBaseInfoEntity (
    val parkingCode: String,
    val name: String,
    val address: String,
    val parkingType: String,
    val parkingTypeName: String,
    val operationRule: String,
    val operationRuleName: String,
    val telNo: String,
    val isCharge: String,
    val isChargeInName: String,
    val groupNumber: String,
    val recentSyncTime: String
)