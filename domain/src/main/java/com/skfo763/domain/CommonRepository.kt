package com.skfo763.domain

import io.reactivex.Single

interface CommonRepository {
    fun getForegroundBarrierState(): Single<Boolean>

    fun setForegroundBarrierState(state: Boolean)
}