package com.kanish.gnewscl.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kanish.gnewscl.data.local.dao.NewsDao
import com.kanish.gnewscl.data.local.entity.NewsResultEntity

@Database(entities = [NewsResultEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao(): NewsDao
}