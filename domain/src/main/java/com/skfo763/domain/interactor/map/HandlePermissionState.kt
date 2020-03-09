package com.skfo763.domain.interactor.map

import com.skfo763.domain.CommonRepository
import com.skfo763.domain.executor.PostExecutionThread
import com.skfo763.domain.executor.ThreadExecutor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HandlePermissionState @Inject constructor(
    val repository: CommonRepository,
    private val executor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) {
    private val compositeDisposable = CompositeDisposable()

    fun getBackgroundViewState(consumer: Consumer<Boolean>) {
        val observer = repository.getForegroundBarrierState()
            .observeOn(Schedulers.from(executor))
            .subscribeOn(uiThread.scheduler)

        compositeDisposable.add(observer.subscribe(consumer))
    }

    fun setMapForegroundState(state: Boolean) {
        repository.setForegroundBarrierState(state)
    }

    fun clearAll() {
        compositeDisposable.clear()
    }
}