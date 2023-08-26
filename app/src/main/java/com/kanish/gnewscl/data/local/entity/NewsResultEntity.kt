package com.kanish.gnewscl.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_result")
data class NewsResultEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id : Int =0,
    @ColumnInfo(name = "category")
    val category: List<String>,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "country")
    val country: List<String>,
    @ColumnInfo(name = "creator")
    val creator: List<String>,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image_url")
    val image_url: String,
    @ColumnInfo(name = "keywords")
    val keywords: List<String>,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "pubDate")
    val pubDate: String,
    @ColumnInfo(name = "source_id")
    val source_id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video_url")
    val video_url: Any
    )
