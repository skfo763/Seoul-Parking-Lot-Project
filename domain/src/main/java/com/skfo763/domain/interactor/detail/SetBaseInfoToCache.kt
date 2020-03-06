package com.skfo763.domain.interactor.detail

import com.skfo763.domain.ParkingLotRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import com.skfo763.domain.model.ParkingLotBaseInfoModel
import com.skfo763.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SetBaseInfoToCache @Inject constructor(
    private val repository: ParkingLotRepository,
    private val executor: ThreadExecutor,
    private val uiThread: PostExecutionThread
): CompletableUseCase<ParkingLotBaseInfoModel>(executor, uiThread) {
    override fun buildUseCaseObservable(params: ParkingLotBaseInfoModel): Completable {
        return repository.saveParkingLotDataIntoCache(params)
    }
}