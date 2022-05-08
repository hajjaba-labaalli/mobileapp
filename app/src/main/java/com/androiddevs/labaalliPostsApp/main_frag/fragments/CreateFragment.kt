package com.androiddevs.labaalliPostsApp.main_frag.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androiddevs.labaalliPostsApp.R
import com.androiddevs.labaalliPostsApp.data.Post
import com.androiddevs.labaalliPostsApp.data.PostCreate
import com.androiddevs.labaalliPostsApp.main_frag.main.MainActivity
import com.androiddevs.labaalliPostsApp.main_frag.main.MainViewModel
import com.androiddevs.labaalliPostsApp.myAdapters.MainRecyclerAdapter
import com.androiddevs.labaalliPostsApp.utilities.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.create_fragment.*



class CreateFragment : Fragment(R.layout.create_fragment) {
    lateinit var viewModel: MainViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        createFab.setOnClickListener() {
                val post = PostCreate(
                    text1.text.toString(),
                    image1.text.toString(),
                    likes.text.toString().toInt(),
                    listOf(
                        Tag11.text.toString(),
                        Tag21.text.toString(),
                        Tag31.text.toString()
                    ),
                    id_user.text.toString()
                )
                viewModel.createPost(post)
                Snackbar.make(view, "Successfully created post..", Snackbar.LENGTH_LONG).apply {
                    show()
                }
        }
    }

}