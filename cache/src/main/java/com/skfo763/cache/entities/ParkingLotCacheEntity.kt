package com.skfo763.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skfo763.cache.DBConstants

@Entity(tableName = DBConstants.ParkingLotTableName)
data class ParkingLotCacheEntity (
    @PrimaryKey
    @ColumnInfo(name = "parking_lot_code")
    val parkingCode: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "parking_type")
    val parkingType: String,

    @ColumnInfo(name = "parking_type_name")
    val parkingTypeName: String,

    @ColumnInfo(name = "operation_rule")
    val operationRule: String,

    @ColumnInfo(name = "operation_rule_name")
    val operationRuleName: String,

    @ColumnInfo(name = "telephone_number")
    val telNo: String,

    @ColumnInfo(name = "is_charge")
    val isCharge: String,

    @ColumnInfo(name = "is_charge_name")
    val isChargeInName: String,

    @ColumnInfo(name = "group_number")
    val groupNumber: String,

    @ColumnInfo(name = "recent_sync_time")
    val recentSyncTime: String
)