package com.kanish.gnewscl.data.repository

import com.kanish.gnewscl.data.entity.NewsResult
import com.kanish.gnewscl.data.network.FeedNetworkService

class NewsFeedRepository(private val networkService: FeedNetworkService) {

    suspend fun fetchNewsFeed(category:String):List<NewsResult>{
        return networkService.fetchNewsFeed(category)
    }
}