package com.skfo763.presentation.model

data class MapDataModel (
    val parkingCode: String,
    val name: String,
    val address: String,val parkingType: String,
    val parkingTypeName: String,
    val operationRule: String,
    val operationRuleName: String,
    val telNo: String,
    val isCharge: String,
    val isChargeInName: String,
    val groupNumber: String,
    val recentSyncTime: String,
    val queueStatus: String,
    val queueStatusName: String,
    val latitude: Double,
    val longitude: Double
)