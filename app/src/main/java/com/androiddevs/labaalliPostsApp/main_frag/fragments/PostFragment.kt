package com.androiddevs.labaalliPostsApp.main_frag.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androiddevs.labaalliPostsApp.R
import com.androiddevs.labaalliPostsApp.main_frag.main.MainActivity
import com.androiddevs.labaalliPostsApp.main_frag.main.MainViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.post_fragment.*
import kotlinx.android.synthetic.main.post_fragment.firstname
import kotlinx.android.synthetic.main.post_fragment.image
import kotlinx.android.synthetic.main.post_fragment.lastname
import kotlinx.android.synthetic.main.post_fragment.picture
import kotlinx.android.synthetic.main.post_fragment.publishDate
import kotlinx.android.synthetic.main.post_fragment.tag1
import kotlinx.android.synthetic.main.post_fragment.tag2
import kotlinx.android.synthetic.main.post_fragment.tag3
import kotlinx.android.synthetic.main.post_fragment.text
import kotlinx.android.synthetic.main.post_grid_item.view.*

class PostFragment : Fragment(R.layout.post_fragment) {

    lateinit var viewModel: MainViewModel
    val args:PostFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val post = args.post
        webView.apply {
            Glide.with(this).load(post.image).into(image)
            Glide.with(this).load(post.owner?.picture).into(picture)
            text.text=post.text
            tag1.text=post.tags?.get(0)
            tag2.text=post.tags?.get(1)
            tag3.text=post.tags?.get(2)
            publishDate.text=post.publishDate
            firstname.text=post.owner?.firstName
            lastname.text=post.owner?.lastName
            title_tag.text=post.owner?.title

        }
    }
}