package com.skfo763.domain.interactor.home

import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class SearchStatusHomeInfo @Inject constructor(
    val repository: ParkingLotRepository,
    executor: ThreadExecutor,
    uiThread: PostExecutionThread
): FlowableUseCase<List<ParkingLotBaseInfoModel>, Boolean>(executor, uiThread) {

    override fun buildUseCaseObservable(param: Boolean): Flowable<List<ParkingLotBaseInfoModel>> {
        return repository.loadBaseInfoWithStatus(param)
    }
}