package com.arctouch.codechallenge.util

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.api.TmdbInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 6000

fun <T> buildApi(serviceClass: Class<T>): T {

    val client = OkHttpClient.Builder()
    client.readTimeout(TIMEOUT, TimeUnit.SECONDS)
    client.connectTimeout(TIMEOUT, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)
    }

    client.addInterceptor(TmdbInterceptor())

    // Build retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client.build())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    return retrofit.build().create(serviceClass)
}