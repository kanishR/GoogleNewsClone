package com.kanish.gnewscl.data.repository

import com.kanish.gnewscl.data.entity.NewsResult
import com.kanish.gnewscl.data.local.dao.NewsDao
import com.kanish.gnewscl.data.local.entity.NewsResultEntity
import com.kanish.gnewscl.data.network.FeedNetworkService

class NewsFeedRepository(private val networkService: FeedNetworkService,private val newsDao:NewsDao) {

    suspend fun fetchNewsFeed(category:String):List<NewsResult>{
         if(!getAllNews().isNullOrEmpty()){
             return getAllNews().map {news ->
                 NewsResult(
                     category = news.category,
                     content = news.content,
                     country = news.country,
                     creator = news.creator,
                     description = news.description,
                     image_url = news.image_url,
                     keywords = news.keywords,
                     language = news.language,
                     link = news.link,
                     pubDate = news.pubDate,
                     source_id = news.source_id,
                     title = news.title,
                     video_url = news.video_url
                 )
             }
         }else {
             val response = networkService.fetchNewsFeed(category)
             if (!response.isNullOrEmpty()) {
                 val newsList = response.map { news ->
                     NewsResultEntity(
                         category = news.category,
                         content = news.content,
                         country = news.country,
                         creator = news.creator,
                         description = news.description,
                         image_url = news.image_url,
                         keywords = news.keywords,
                         language = news.language,
                         link = news.link,
                         pubDate = news.pubDate,
                         source_id = news.source_id,
                         title = news.title,
                         video_url = news.video_url
                     )
                 }
                 newsDao.insertAll(newsList)
             }
             return getAllNews().map {news ->
                 NewsResult(
                     category = news.category,
                     content = news.content,
                     country = news.country,
                     creator = news.creator,
                     description = news.description,
                     image_url = news.image_url,
                     keywords = news.keywords,
                     language = news.language,
                     link = news.link,
                     pubDate = news.pubDate,
                     source_id = news.source_id,
                     title = news.title,
                     video_url = news.video_url
                 )
             }
             }

    }
    suspend fun getAllNews(): List<NewsResultEntity> {
        return  newsDao.getAllNews()
    }
}