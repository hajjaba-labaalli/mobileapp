package com.androiddevs.labaalliPostsApp.data

data class MyData(
    val data: MutableList<Post>,
    val limit: Int,
    val page: Int,
    val total: Int
)