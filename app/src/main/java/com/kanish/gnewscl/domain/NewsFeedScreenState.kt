package com.kanish.gnewscl.domain

import com.kanish.gnewscl.data.entity.NewsResult

sealed class NewsFeedScreenState{
    object Loading :NewsFeedScreenState()
    object FeedError : NewsFeedScreenState()
    data class NewsFeedAvailable(val result:List<NewsResult>) :NewsFeedScreenState()

}
