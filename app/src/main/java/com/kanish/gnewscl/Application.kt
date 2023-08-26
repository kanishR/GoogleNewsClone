package com.kanish.gnewscl

import android.app.Application
import androidx.room.Room
import com.kanish.gnewscl.data.local.db.NewsDatabase

class  NewsApplication : Application() {
    companion object {
        lateinit var newsDatabase: NewsDatabase

    }

    override fun onCreate() {
        super.onCreate()

        newsDatabase=  Room.databaseBuilder(
            applicationContext,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }
    fun getNewsDb():NewsDatabase{
        return newsDatabase
    }
}