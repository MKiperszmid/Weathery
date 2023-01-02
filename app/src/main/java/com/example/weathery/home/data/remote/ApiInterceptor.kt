package com.example.weathery.home.data.remote

import com.example.weathery.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val urlBuilder = originalHttpUrl.newBuilder()
        val requestBuilder = original.newBuilder()

        urlBuilder.addQueryParameter("appid", BuildConfig.API_KEY)

        val httpUrl = urlBuilder.build()
        requestBuilder.url(httpUrl)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}