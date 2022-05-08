package com.androiddevs.labaalliPostsApp.api

import com.androiddevs.labaalliPostsApp.data.MyData
import com.androiddevs.labaalliPostsApp.data.Post
import com.androiddevs.labaalliPostsApp.data.PostCreate
import retrofit2.Response
import retrofit2.http.*

interface PostService {

    @GET("post?page")
    suspend fun getPosts(
        @Query("page")
        pageNumber: Int,
        ): Response<MyData>

    @DELETE("post/{id}")
    suspend fun deletePost(
        @Path("id")
        id : String
    ):Response<String>

    @POST("post/create")
    suspend fun createPost(
        @Body params : PostCreate
    ) : Response<Post>

    @GET("post/{id}")
    suspend fun getPost(
        @Path("id")
        id: String?
    ): Response<Post>

    @GET("tag/{id}/post")
    suspend fun searchPost(
        @Path("id")
        searchQuery: String,
        @Query("page")
        pageNumber: Int
    ): Response<MyData>
}