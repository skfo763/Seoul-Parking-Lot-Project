package com.skfo763.presentation

import androidx.lifecycle.MutableLiveData
import com.skfo763.presentation.resource.Resource
import com.skfo763.presentation.resource.ResourceState
import io.reactivex.subscribers.DisposableSubscriber

abstract class DataSubscriber<V, D>(
    private val liveData: MutableLiveData<Resource<List<V>>>
): DisposableSubscriber<List<D>>() {

    abstract val mapFunction: (D) -> (V)

    override fun onComplete() {}

    override fun onNext(t: List<D>?) {
        liveData.postValue(
            Resource(ResourceState.SUCCESS, data = t?.map { mapFunction.invoke(it) }))
    }

    override fun onError(t: Throwable) {
        liveData.postValue(Resource(ResourceState.ERROR, t.message))
    }
}