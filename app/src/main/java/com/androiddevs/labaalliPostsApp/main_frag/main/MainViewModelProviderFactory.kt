package com.androiddevs.labaalliPostsApp.main_frag.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.labaalliPostsApp.repository.PostRepository

class MainViewModelProviderFactory(
    val postRepo: PostRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(postRepo) as T
    }
}