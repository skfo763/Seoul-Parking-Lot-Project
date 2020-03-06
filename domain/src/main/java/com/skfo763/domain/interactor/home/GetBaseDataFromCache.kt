package com.skfo763.domain.interactor.home

import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetBaseDataFromCache @Inject constructor(
    private val repository: ParkingLotRepository,
    private val executor: ThreadExecutor,
    private val uiThread: PostExecutionThread
): FlowableUseCase<List<ParkingLotBaseInfoModel>, String>(executor, uiThread) {

    override fun buildUseCaseObservable(param: String): Flowable<List<ParkingLotBaseInfoModel>> {
        return repository.loadAllBaseInfoDataFromCache()
    }
}