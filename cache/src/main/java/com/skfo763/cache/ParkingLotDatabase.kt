package com.skfo763.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skfo763.cache.dao.ParkingLotCacheDao
import com.skfo763.cache.entities.ParkingLotCacheEntity

@Database(entities = [ParkingLotCacheEntity::class], version = 1)
abstract class ParkingLotDatabase : RoomDatabase() {

    abstract fun parkingLotCacheDao(): ParkingLotCacheDao
    private var INSTANCE: ParkingLotDatabase? = null
    private val sLock = Any()

    fun getInstance(context: Context): ParkingLotDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ParkingLotDatabase::class.java,
                        DBConstants.DatabaseName
                    ).build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}