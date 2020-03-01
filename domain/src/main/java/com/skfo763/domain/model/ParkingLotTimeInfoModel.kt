package com.skfo763.domain.model

data class ParkingLotTimeInfoModel(
    val weekdayBeginTime: String,
    val weekdayEndTime: String,
    val weekendStartTime: String,
    val weekendEndTime: String,
    val holidayBeginTime: String,
    val holidayEndTime: String
)