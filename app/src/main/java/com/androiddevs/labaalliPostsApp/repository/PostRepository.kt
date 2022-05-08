package com.androiddevs.labaalliPostsApp.repository

import com.android_dev.labaalli_posts_app.api.RetrofitInstance
import com.androiddevs.labaalliPostsApp.data.PostCreate

class PostRepository() {

    suspend fun getPosts(pagNumber: Int) =
        RetrofitInstance.api.getPosts(pagNumber)
    suspend fun deletePost(id: String) =
        RetrofitInstance.api.deletePost(id)
    suspend fun createPost(post: PostCreate)=
        RetrofitInstance.api.createPost(post)
    suspend fun getPost(id: String?)=
        RetrofitInstance.api.getPost(id)
    suspend fun searchPost(searchQuery: String,pageNumber: Int)=
        RetrofitInstance.api.searchPost(searchQuery,pageNumber)

}