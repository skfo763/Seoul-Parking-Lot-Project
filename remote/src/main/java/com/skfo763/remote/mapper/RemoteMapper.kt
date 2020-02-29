package com.skfo763.remote.mapper

interface RemoteMapper<R, B, S> {

    fun mappingToBaseInfo(type: R): B

    fun mappingToSpecificInfo(type: R): S
}