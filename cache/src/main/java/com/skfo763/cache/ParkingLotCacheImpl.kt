package com.skfo763.cache

import com.skfo763.cache.mapper.EntityMapperImpl
import com.skfo763.cache.sharedprefs.SharedPreferenceHelper
import com.skfo763.data.entities.ParkingLotBaseInfoEntity
import com.skfo763.data.repository.parkinglot.ParkingLotCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ParkingLotCacheImpl
@Inject constructor(val db: ParkingLotDatabase,
                    private val mapper: EntityMapperImpl,
                    private val preferenceHelper: SharedPreferenceHelper) :
    ParkingLotCache {

    internal fun getDatabase(): ParkingLotDatabase = db

    override fun clearCaches(): Completable {
        return Completable.defer {
            db.parkingLotCacheDao().clearAllCaches()
            Completable.complete()
        }
    }

    override fun saveParkingLotInfoToCache(data: ParkingLotBaseInfoEntity): Completable {
        return Completable.defer {
            db.parkingLotCacheDao().insertCaches(mapper.mappingDataToCache(data))
            Completable.complete()
        }
    }

    override fun getAllCaches(): Flowable<List<ParkingLotBaseInfoEntity>> {
        return Flowable.just(db.parkingLotCacheDao().loadAllCacheData())
            .map {
                it.map { entity -> mapper.mappingDataFromCache(entity)}
            }
    }

    override fun getCacheDataWithRegion(region: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        return Flowable.just(db.parkingLotCacheDao().loadCacheDataWithAddress(region))
            .map {
                it.map { entity -> mapper.mappingDataFromCache(entity) }
            }
    }

    override fun getCacheDataWithChargeInfo(isCharge: String): Flowable<List<ParkingLotBaseInfoEntity>> {
        return Flowable.just(db.parkingLotCacheDao().loadAllCacheData())
            .map {
                it.map { entity -> mapper.mappingDataFromCache(entity) }
            }
    }

    override fun isCached(): Single<Boolean> {
        return Single.create {
            it.onSuccess(db.parkingLotCacheDao().loadAllCacheData().isNotEmpty())
        }
    }

    override fun setLastCacheTime(lastCacheTime: Long) {
        preferenceHelper.lastCacheTime = lastCacheTime
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastCacheUpdateTime = getLastCacheUpdateTime()
        return currentTime - lastCacheUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTime(): Long {
        return preferenceHelper.lastCacheTime
    }

    companion object {
        private const val EXPIRATION_TIME: Long = 60 * 10 * 1000
    }
}