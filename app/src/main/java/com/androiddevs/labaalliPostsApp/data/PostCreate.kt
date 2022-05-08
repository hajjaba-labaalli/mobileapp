package com.androiddevs.labaalliPostsApp.data

data class PostCreate (
    val text: String,
    val image: String,
    val likes: Int,
    val tags: List<String>,
    val owner: String
    ){

}