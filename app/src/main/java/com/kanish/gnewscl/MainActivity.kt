package com.kanish.gnewscl

import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kanish.gnewscl.data.ApiService
import com.kanish.gnewscl.data.RetrofitClient
import com.kanish.gnewscl.data.network.FeedNetworkService
import com.kanish.gnewscl.data.repository.NewsFeedRepository
import com.kanish.gnewscl.domain.NewsFeedScreenState
import com.kanish.gnewscl.ui.FeedVmFactory
import com.kanish.gnewscl.ui.NewsFeedFragment
import com.kanish.gnewscl.ui.NewsFeedViewModel

class MainActivity : AppCompatActivity() {
    private val newsFeedViewModel : NewsFeedViewModel by lazy {
        val apiService=RetrofitClient.apiService
        val networkService= FeedNetworkService(apiService)
        val newsRepository = NewsFeedRepository(networkService)
       ViewModelProvider(this, factory = FeedVmFactory(newsRepository, application ))[NewsFeedViewModel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        subscribe()
//        newsFeedViewModel.fetchNewsFeed()
        supportFragmentManager.beginTransaction().add(R.id.flNews,NewsFeedFragment()).addToBackStack("newsFrag").commitAllowingStateLoss()
    }

    private fun subscribe() {
        newsFeedViewModel.mNewsFeedState.observe(this) {
            when (it) {
                NewsFeedScreenState.FeedError -> {
                    Log.d("kanish","FeedError")
                }
                NewsFeedScreenState.Loading -> {
                    Log.d("kanish","FeedLoading")
                }
                is NewsFeedScreenState.NewsFeedAvailable -> {
                    Log.d("kanish","FeedSuccess>>"+ it.result)
                }
            }
        }
    }
}