package com.kanish.gnewscl.data.entity

data class ApiNewsFeed(
    val nextPage: Any,
    val results: List<NewsResult>,
    val status: String,
    val totalResults: Int
)