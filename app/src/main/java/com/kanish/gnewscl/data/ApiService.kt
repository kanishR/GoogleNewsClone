package com.kanish.gnewscl.data

import com.kanish.gnewscl.data.entity.ApiNewsFeed
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/1/news")
    suspend fun fetchNewsApi(@Query("apiKey") apiKey:String,@Query("category") category:String, @Query("country") country:String) : ApiNewsFeed
}