package com.skfo763.cache.dao

import androidx.room.*
import com.skfo763.cache.DBConstants
import com.skfo763.cache.entities.ParkingLotCacheEntity

@Dao
interface ParkingLotCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCaches(entity: ParkingLotCacheEntity)

    @Delete
    fun deleteCaches(entity: ParkingLotCacheEntity)

    @Query("DELETE FROM ${DBConstants.ParkingLotTableName}")
    fun clearAllCaches()

    @Query("SELECT * FROM ${DBConstants.ParkingLotTableName}")
    fun loadAllCacheData(): List<ParkingLotCacheEntity>

    @Query("SELECT * FROM ${DBConstants.ParkingLotTableName} WHERE address = :address")
    fun loadCacheDataWithAddress(address: String): List<ParkingLotCacheEntity>

    @Query("SELECT * FROM ${DBConstants.ParkingLotTableName} WHERE is_charge = :isCharge")
    fun loadCacheDataWithChargeInfo(isCharge: String): List<ParkingLotCacheEntity>
}