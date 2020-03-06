package com.skfo763.domain.interactor.detail

import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import com.skfo763.domain.model.ParkingLotTimeInfoModel
import com.skfo763.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetDetailTimeInfo @Inject constructor(
    private val repository: ParkingLotRepository,
    private val executor: ThreadExecutor,
    private val uiThread: PostExecutionThread
): FlowableUseCase<List<ParkingLotTimeInfoModel>, String>(executor, uiThread) {

    override fun buildUseCaseObservable(param: String): Flowable<List<ParkingLotTimeInfoModel>> {
        return repository.loadTimeInfo(param)
    }
}