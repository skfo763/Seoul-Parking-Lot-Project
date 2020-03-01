package com.skfo763.domain.model

data class ParkingLotPriceModel(
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
)