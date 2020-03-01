package com.skfo763.domain.model

class ParkingLotCurrentInfoModel (
    val queueStatus: String,
    val queueStatusName: String,
    val capacity: Double,
    val currentParking: Double,
    val currentParkingUpdateTime: String
)