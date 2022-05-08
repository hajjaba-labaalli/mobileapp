package com.android_dev.labaalli_posts_app.api

import com.androiddevs.labaalliPostsApp.api.PostService
import com.androiddevs.labaalliPostsApp.utilities.WEB_SERVICE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().apply{
        addInterceptor(MyInterceptor() )
        }.build()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WEB_SERVICE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PostService by lazy {
        retrofit.create(PostService::class.java)
    }

}