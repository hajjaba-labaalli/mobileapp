package com.androiddevs.labaalliPostsApp.main_frag.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.labaalliPostsApp.R
import com.androiddevs.labaalliPostsApp.data.MyData
import com.androiddevs.labaalliPostsApp.myAdapters.MainRecyclerAdapter
import com.androiddevs.labaalliPostsApp.main_frag.main.MainActivity
import com.androiddevs.labaalliPostsApp.main_frag.main.MainViewModel
import com.androiddevs.labaalliPostsApp.utilities.QUERY_PAGE_SIZE
import com.androiddevs.labaalliPostsApp.utilities.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.paginationProgressBar
import kotlinx.android.synthetic.main.search_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    lateinit var viewModel: MainViewModel
    lateinit var postsAdapter: MainRecyclerAdapter

    val TAG = "MainFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        postsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("post",it)
            }
            findNavController().navigate(
                R.id.action_mainFragment_to_postFragment,
                bundle
            )
        }


        viewModel.posts.observe(viewLifecycleOwner, Observer {   response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let{ postResponse ->
                        postsAdapter.differ.submitList(postResponse.data.toList())
                        val totalPages  = postResponse.total / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingPostsPage == totalPages
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let{message->
                        Log.e(TAG,"An error occured: $message")
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.searchPost(etSearch.toString())
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupRecyclerView() {
        postsAdapter = MainRecyclerAdapter()
        rvPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@MainFragment.scrollListener)
        }

    }

}