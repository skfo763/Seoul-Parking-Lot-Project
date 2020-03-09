package com.skfo763.domain.interactor.map

import com.skfo763.core.PermissionState
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

    fun getBackgroundViewState(consumer: Consumer<PermissionState.Type>) {
        val observer = repository.getLocationPermissionState()
            .observeOn(Schedulers.from(executor))
            .subscribeOn(uiThread.scheduler)

        compositeDisposable.add(observer.subscribe(consumer))
    }

    fun setLocationPermissionState(state: PermissionState.Type) {
        repository.setLocationPermissionState(state)
    }

    fun clearAll() {
        compositeDisposable.clear()
    }
}