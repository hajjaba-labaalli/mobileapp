package com.androiddevs.labaalliPostsApp.main_frag.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.labaalliPostsApp.data.MyData
import com.androiddevs.labaalliPostsApp.data.Post
import com.androiddevs.labaalliPostsApp.data.PostCreate
import com.androiddevs.labaalliPostsApp.myAdapters.MainRecyclerAdapter
import com.androiddevs.labaalliPostsApp.repository.PostRepository
import com.androiddevs.labaalliPostsApp.utilities.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    val postRepo: PostRepository
)  : ViewModel(){
    lateinit var postsAdapter: MainRecyclerAdapter

    var myData = listOf<MyData>()

    var posts: MutableLiveData<Resource<MyData>> = MutableLiveData()
    var breakingPostsPage = 0
    var breakingPostsResponse: MyData ?= null

    var searchPost: MutableLiveData<Resource<MyData>> = MutableLiveData()
    var searchPostsPage = 0
    var searchPostsResponse: MyData? = null

    var newPostList: MutableLiveData<Resource<String>> = MutableLiveData()


    var newPostList2: MutableLiveData<Resource<Post>> = MutableLiveData()


    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        posts.postValue(Resource.Loading())
        val response = postRepo.getPosts(this@MainViewModel.breakingPostsPage)
        posts.postValue(handlePostsResponse(response))
    }


    fun searchPost(searchQuery: String)=viewModelScope.launch {
        searchPost.postValue(Resource.Loading())
        val response = postRepo.searchPost(searchQuery, this@MainViewModel.searchPostsPage)
        searchPost.postValue(handleSearchPostResponse(response))
    }

    fun deletePost(id: String?)=viewModelScope.launch {
        newPostList.postValue(Resource.Loading())
        val response = postRepo.deletePost(id!!)
        newPostList.postValue(handleDeletePostResponse(response))
    }

    fun createPost(params : PostCreate)=viewModelScope.launch{
        newPostList2.postValue(Resource.Loading())
        val response = postRepo.createPost(params)
        newPostList2.postValue(handleCreatePostResponse(response))

    }

    private fun handleCreatePostResponse(response: Response<Post>): Resource<Post> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleDeletePostResponse(response: Response<String>): Resource<String> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlePostsResponse(response: Response<MyData>): Resource<MyData> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingPostsPage++
                if (breakingPostsResponse == null){
                    breakingPostsResponse = resultResponse
                }else{
                    val oldPosts = breakingPostsResponse?.data
                    val newPosts = resultResponse.data
                    oldPosts?.addAll(newPosts)
                }

                return Resource.Success(breakingPostsResponse ?: resultResponse)
            }

        }
        return Resource.Error(response.message())
    }

    private fun handleSearchPostResponse(response: Response<MyData>): Resource<MyData> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchPostsPage++
                if (searchPostsResponse == null){
                    breakingPostsResponse = resultResponse
                }else{

                    var oldPosts = searchPostsResponse?.data
                    val newPosts = resultResponse.data
                    oldPosts?.addAll(newPosts)
                    postsAdapter.differ.submitList(newPosts)
                }
                return Resource.Success(searchPostsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }





}