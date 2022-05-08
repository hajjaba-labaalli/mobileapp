package com.androiddevs.labaalliPostsApp.data


import java.io.Serializable

data class Post(
    val idFavoritesPosts: Int? = null,
    val id: String,
    val image: String?,
    val likes: Int?,
    val owner: Owner?,
    val publishDate: String?,
    val tags: List<String>?,
    val text: String?
):Serializable