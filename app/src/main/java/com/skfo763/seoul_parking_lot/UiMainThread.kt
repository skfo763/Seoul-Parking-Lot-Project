package com.skfo763.seoul_parking_lot

import com.skfo763.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UiMainThread @Inject constructor(): PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}