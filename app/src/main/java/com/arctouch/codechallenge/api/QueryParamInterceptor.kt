package com.arctouch.codechallenge.api

import com.arctouch.codechallenge.util.API_KEY
import com.arctouch.codechallenge.util.DEFAULT_LANGUAGE
import com.arctouch.codechallenge.util.QUERY_API_KEY
import com.arctouch.codechallenge.util.QUERY_LANGUAGE
import okhttp3.Interceptor
import okhttp3.Response

class TmdbInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val httpUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .addQueryParameter(QUERY_LANGUAGE, DEFAULT_LANGUAGE)
            .build()

        return chain.proceed(
            request.newBuilder().url(httpUrl).build()
        )
    }

}