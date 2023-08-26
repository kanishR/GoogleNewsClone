package com.kanish.gnewscl.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanish.gnewscl.data.local.entity.NewsResultEntity
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsList: List<NewsResultEntity>)
    @Query("SELECT * FROM news_results")
    suspend fun getAllNews(): List<NewsResultEntity>
    //add pagination query here
    @Query("SELECT * FROM news_results ORDER BY id DESC LIMIT :pageSize OFFSET :offset")
    suspend fun getPaginatedNews(pageSize: Int, offset: Int): List<NewsResultEntity>

}