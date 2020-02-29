package com.skfo763.remote

import com.google.gson.Gson
import com.skfo763.remote.constants.ParkingLotApiConstant
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroBuilder {
    private object Holder {
        val INSTANCE = RetroBuilder()
    }

    companion object {
        val i: RetroBuilder by lazy { Holder.INSTANCE }
    }

    private val gson = Gson()

    fun getRetrofit(isDebug: Boolean): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ParkingLotApiConstant.OPEN_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getOkHttpclientBuilder(isDebug))
            .build()
    }

    private fun getOkHttpclientBuilder(isDebug: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor(isDebug))
            .build()
    }

    private fun getLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if(isDebug) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
        }
    }
}