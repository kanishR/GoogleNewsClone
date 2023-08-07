package com.kanish.gnewscl

import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.kanish.gnewscl.data.ApiService
import com.kanish.gnewscl.data.RetrofitClient
import com.kanish.gnewscl.data.network.FeedNetworkService
import com.kanish.gnewscl.data.repository.NewsFeedRepository
import com.kanish.gnewscl.ui.FeedVmFactory
import com.kanish.gnewscl.ui.NewsFeedViewModel

class MainActivity : AppCompatActivity() {
    private val newsFeedViewModel : NewsFeedViewModel by lazy {
        val apiService=RetrofitClient.apiService
        val networkService:FeedNetworkService= FeedNetworkService(apiService)
        val newsRepository = NewsFeedRepository(networkService)
       ViewModelProvider(this, factory = FeedVmFactory(newsRepository, application ))[NewsFeedViewModel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsFeedViewModel.fetchNewsFeed()
    }
}