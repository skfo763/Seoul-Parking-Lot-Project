package com.skfo763.cache.mapper

/*
    B : Before mapping to cache is processed
    A : After mapping to cache is processed
 */
interface EntityMapper<B, A> {

    fun mappingDataToCache(type: B): A

    fun mappingDataFromCache(type: A): B
}