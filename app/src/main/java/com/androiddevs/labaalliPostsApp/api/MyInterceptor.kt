package com.android_dev.labaalli_posts_app.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("app-id", "6257e31d9af5bf094491e4f1")
            .build()
        return chain.proceed(request)

    }
}