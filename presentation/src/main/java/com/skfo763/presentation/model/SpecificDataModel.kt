package com.skfo763.presentation.model

sealed class SpecificDataModel

data class BaseInfoViewHolderModel(
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
    val recentSyncTime: String,
    val queueStatus: String,
    val queueStatusName: String,
    val latitude: Double,
    val longitude: Double
): SpecificDataModel()

data class TimeViewHolderModel (
    val weekdayBeginTime: String,
    val weekdayEndTime: String,
    val weekendStartTime: String,
    val weekendEndTime: String,
    val holidayBeginTime: String,
    val holidayEndTime: String
): SpecificDataModel()

data class PriceViewHolderModel (
    val isNightFree: String,
    val isNightFreeInName: String,
    val isSaturdayFree: String,
    val isSaturdayFreeInName: String,
    val isHolidayFree: String,
    val isHolidayFreeInName: String,
    val priceInMonthlyFulltime: String,
    val price: Double,
    val pricePerMinute: Double,
    val additionalPrice: Double,
    val additionalPricePerMinute: Double,
    val priceForBus: Double,
    val pricePerMinuteForBus: Double,
    val additionalPriceForBus: Double,
    val additionalPricePerMinuteForBus: Double,
    val dailyMaximumCharge: Double
): SpecificDataModel()

data class ParkingLotLocationModel (
    val latitude: Double,
    val longitude: Double,
    val assignCode: String,
    val assignCodeName: String
): SpecificDataModel()

data class ParkingLotCurrentInfoModel (
    val queueStatus: String,
    val queueStatusName: String,
    val capacity: Double,
    val currentParking: Double,
    val currentParkingUpdateTime: String
): SpecificDataModel()