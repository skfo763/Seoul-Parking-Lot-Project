package com.skfo763.domain.interactor.cache

import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetCacheBaseInfo @Inject constructor(
    private val repository: ParkingLotRepository,
    executor: ThreadExecutor,
    uiThread: PostExecutionThread
): FlowableUseCase<List<ParkingLotBaseInfoModel>, Void?>(executor, uiThread) {

    override fun buildUseCaseObservable(param: Void?): Flowable<List<ParkingLotBaseInfoModel>> {
        return repository.loadAllBaseInfoDataFromCache()
    }
}