package com.skfo763.data

import com.skfo763.data.mapper.DataMapperImpl
import com.skfo763.data.source.ParkingLotDataSourceFactory
import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.model.*
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ParkingLotDataRepository
@Inject constructor(private val factory: ParkingLotDataSourceFactory,
                    private val mapper: DataMapperImpl): ParkingLotRepository {
    override fun clearCache(): Completable {
        return factory.getCacheDataSource().clearParkingLotData()
    }

    override fun saveParkingLotDataIntoCache(data: ParkingLotBaseInfoModel): Completable {
        return factory.getCacheDataSource().saveParkingLotData(
            mapper.mapBaseModelToEntity(data)
        )
    }

    override fun loadAllBaseInfoData(): Flowable<List<ParkingLotBaseInfoModel>> {
        return factory.getRemoteDataSource().getParkingLotBaseData()
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapBaseModelFromEntity(entity)
                })
            }
    }

    override fun loadAllBaseInfoDataFromCache(): Flowable<List<ParkingLotBaseInfoModel>> {
        return factory.getCacheDataSource().isCached()
            .flatMapPublisher {
                factory.getDataSoruce(it).getParkingLotBaseData()
            }
            .flatMap {
                Flowable.just(it.map { entity -> mapper.mapBaseModelFromEntity(entity) })
            }
    }

    override fun loadBaseInfoWithRegion(region: String): Flowable<List<ParkingLotBaseInfoModel>> {
        return factory.getRemoteDataSource().getParkingLotBaseDataWithRegion(region)
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapBaseModelFromEntity(entity)
                })
            }
    }

    override fun loadBaseInfoWithStatus(status: Boolean): Flowable<List<ParkingLotBaseInfoModel>> {
        return factory.getRemoteDataSource().getParkingLotBaseDataWithStatus(status)
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapBaseModelFromEntity(entity)
                })
            }
    }

    override fun loadCurrentInfoData(id: String): Flowable<List<ParkingLotCurrentInfoModel>> {
        return factory.getRemoteDataSource().getParkingLotSpecificDataWithId(id)
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapCurrentInfoModelFromEntity(entity)
                })
            }
    }

    override fun loadLocationInfo(id: String): Flowable<List<ParkingLotLocationModel>> {
        return factory.getRemoteDataSource().getParkingLotSpecificDataWithId(id)
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapLocationModelFromEntity(entity)
                })
            }
    }

    override fun loadPriceInfo(id: String): Flowable<List<ParkingLotPriceModel>> {
        return factory.getRemoteDataSource().getParkingLotSpecificDataWithId(id)
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapPriceModelFromEntity(entity)
                })
            }
    }

    override fun loadTimeInfo(id: String): Flowable<List<ParkingLotTimeInfoModel>> {
        return factory.getRemoteDataSource().getParkingLotSpecificDataWithId(id)
            .flatMap {
                Flowable.just(it.map { entity ->
                    mapper.mapTimeInfoModelFromEntity(entity)
                })
            }
    }

}