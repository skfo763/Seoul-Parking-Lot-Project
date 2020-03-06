package com.skfo763.domain.interactor.detail

import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import com.skfo763.domain.model.ParkingLotCurrentInfoModel
import com.skfo763.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetDetailCurrentInfo @Inject constructor(
    private val repository: ParkingLotRepository,
    executor: ThreadExecutor,
    uiThread: PostExecutionThread
): FlowableUseCase<List<ParkingLotCurrentInfoModel>, String>(executor, uiThread) {

    override fun buildUseCaseObservable(param: String): Flowable<List<ParkingLotCurrentInfoModel>> {
        return repository.loadCurrentInfoData(param)
    }
}