package com.androiddevs.labaalliPostsApp.main_frag.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.labaalliPostsApp.R
import com.androiddevs.labaalliPostsApp.repository.PostRepository
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        val postRepository = PostRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(postRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        bottomNavigationView.setupWithNavController(postsNavHostFragment.findNavController())
    }
}
