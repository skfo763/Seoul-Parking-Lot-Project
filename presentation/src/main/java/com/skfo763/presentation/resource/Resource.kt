package com.skfo763.presentation.resource

open class Resource<T> constructor(
    val resState: ResourceState,
    val message: String? = "",
    val data: T? = null
) {

    fun<T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, "success state", data)
    }

    fun<T> error(message: String, data: T?): Resource<T> {
        return Resource(ResourceState.ERROR, message, data)
    }

    fun<T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, data = null)
    }
}