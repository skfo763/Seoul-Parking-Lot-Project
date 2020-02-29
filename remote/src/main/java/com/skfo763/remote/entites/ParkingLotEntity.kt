package com.skfo.entity

import com.google.gson.annotations.SerializedName

/*
    PARKING_NAME	        주차장명
    ADDR	                주소
    PARKING_CODE	        주차장코드
    PARKING_TYPE	        주차장 종류
    PARKING_TYPE_NM	        주차장 종류명
    OPERATION_RULE	        운영구분
    OPERATION_RULE_NM	    운영구분명
    TEL	                    전화번호
    QUE_STATUS	            주차현황 정보 제공여부
    QUE_STATUS_NM	        주차현황 정보 제공여부명
    CAPACITY	            주차 면(주차 가능 차량 수)
    CUR_PARKING	            현재 주차중인 대수
    CUR_PARKING_TIME	    현재 주차 차량 업데이트 시간
    PAY_YN	                유무료구분
	PAY_NM	                유무료구분명
	NIGHT_FREE_OPEN	        야간무료개방여부
	NIGHT_FREE_OPEN_NM	    야간무료개방여부명
	WEEKDAY_BEGIN_TIME	    평일 운영 시작시각(HHMM)
	WEEKDAY_END_TIME	    평일 운영 종료시각(HHMM)
	WEEKEND_BEGIN_TIME	    주말 운영 시작시각(HHMM)
	WEEKEND_END_TIME	    주말 운영 종료시각(HHMM)
	HOLIDAY_BEGIN_TIME	    공휴일 운영 시작시각(HHMM)
	HOLIDAY_END_TIME	    공휴일 운영 종료시각(HHMM)
	SYNC_TIME	            최종데이터 동기화 시간
	SATURDAY_PAY_YN	        토요일 유,무료 구분
	SATURDAY_PAY_NM	        토요일 유,무료 구분명
	HOLIDAY_PAY_YN	        공휴일 유,무료 구분
	HOLIDAY_PAY_NM	        공휴일 유,무료 구분명
	FULLTIME_MONTHLY	    월 정기권 금액
	GRP_PARKNM	            노상 주차장 관리그룹번호
	RATES	                기본 주차 요금
	TIME_RATE	            기본 주차 시간(분 단위)
	ADD_RATES	            추가 단위 요금
	ADD_TIME_RATE	        추가 단위 시간(분 단위)
	BUS_RATES	            버스 기본 주차 요금
	BUS_TIME_RATE	        버스 기본 주차 시간(분 단위)
	BUS_ADD_TIME_RATE	    버스 추가 단위 시간(분 단위)
	BUS_ADD_RATES	        버스 추가 단위 요금
	DAY_MAXIMUM	            일 최대 요금
	LAT	                    주차장 위치 좌표 위도
	LNG	                    주차장 위치 좌표 경도
	ASSIGN_CODE	            배정코드
	ASSIGN_CODE_NM	        배정코드명
 */
data class ParkingLotEntity(
    @SerializedName("PARKING_NAME") val name: String,
    @SerializedName("ADDR") val address: String,
    @SerializedName("PARKING_CODE") val parkingCode: String,
    @SerializedName("PARKING_TYPE") val parkingType: String,
    @SerializedName("PARKING_TYPE_NM") val parkingTypeName: String,
    @SerializedName("OPERATION_RULE") val operationRule: String,
    @SerializedName("OPERATION_RULE_NM") val operationRuleName: String,
    @SerializedName("TEL") val telNo: String,
    @SerializedName("QUE_STATUS") val queueStatus: String,
    @SerializedName("QUE_STATUS_NM") val queueStatusName: String,
    @SerializedName("CAPACITY") val capacity: Double,
    @SerializedName("CUR_PARKING") val currentParking: Double,
    @SerializedName("CUR_PARKING_TIME") val currentParkingUpdateTime: String,
    @SerializedName("PAY_YN") val isCharge: String,
    @SerializedName("PAY_NM") val isChargeInName: String,
    @SerializedName("NIGHT_FREE_OPEN") val isNightFree: String,
    @SerializedName("NIGHT_FREE_OPEN_NM") val isNightFreeInName: String,
    @SerializedName("WEEKDAY_BEGIN_TIME") val weekdayBeginTime: String,
    @SerializedName("WEEKDAY_END_TIME") val weekdayEndTime: String,
    @SerializedName("WEEKEND_BEGIN_TIME") val weekendStartTime: String,
    @SerializedName("WEEKEND_END_TIME") val weekendEndTime: String,
    @SerializedName("HOLIDAY_BEGIN_TIME") val holidayBeginTime: String,
    @SerializedName("HOLIDAY_END_TIME") val holidayEndTime: String,
    @SerializedName("SYNC_TIME") val recentSyncTime: String,
    @SerializedName("SATURDAY_PAY_YN") val isSaturdayFree: String,
    @SerializedName("SATURDAY_PAY_NM") val isSaturdayFreeInName: String,
    @SerializedName("HOLIDAY_PAY_YN") val isHolidayFree: String,
    @SerializedName("HOLIDAY_PAY_NM") val isHolidayFreeInName: String,
    @SerializedName("FULLTIME_MONTHLY") val priceInMonthlyFulltime: String,
    @SerializedName("GRP_PARKNM") val groupNumber: String,
    @SerializedName("RATES") val price: Double,
    @SerializedName("TIME_RATE") val pricePerMinute: Double,
    @SerializedName("ADD_RATES") val additionalPrice: Double,
    @SerializedName("ADD_TIME_RATE") val additionalPricePerMinute: Double,
    @SerializedName("BUS_RATES") val priceForBus: Double,
    @SerializedName("BUS_TIME_RATE") val pricePerMinuteForBus: Double,
    @SerializedName("BUS_ADD_TIME_RATE") val additionalPriceForBus: Double,
    @SerializedName("BUS_ADD_RATES") val additionalPricePerMinuteForBus: Double,
    @SerializedName("DAY_MAXIMUM") val dailyMaximumCharge: Double,
    @SerializedName("LAT") val latitude: Double,
    @SerializedName("LNG") val longitude: Double,
    @SerializedName("ASSIGN_CODE") val assignCode: String,
    @SerializedName("ASSIGN_CODE_NM") val assignCodeName: String
)

