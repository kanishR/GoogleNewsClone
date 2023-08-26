package com.kanish.gnewscl.data.network

import com.kanish.gnewscl.BuildConfig
import com.kanish.gnewscl.data.ApiService
import com.kanish.gnewscl.data.entity.NewsResult

/**
 * FeedNetworkService class acts as source of truth for remote data,
 * Although for small project like this we can directly call apiService from repository
 * but , Using this class we can filter out error and success and return a contracted data type
 * to repository layer
 */
class FeedNetworkService(val apiService: ApiService) {

    suspend fun fetchNewsFeed(category:String):List<NewsResult>{
        return try {
            val result = apiService.fetchNewsApi(BuildConfig.API_KEY,"top","in")
            result.results
        }catch (e:Exception){
            e.printStackTrace()
            arrayListOf()
        }

    }
}