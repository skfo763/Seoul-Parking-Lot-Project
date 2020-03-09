package com.skfo763.data.source.common

import com.skfo763.data.repository.common.CommonDataSource
import javax.inject.Inject

class CommonDataSourceFactory @Inject constructor(
    private val cacheDataSource: CommonCacheDataSource
) {

    fun getDataSource(): CommonDataSource {
        return cacheDataSource
    }
}