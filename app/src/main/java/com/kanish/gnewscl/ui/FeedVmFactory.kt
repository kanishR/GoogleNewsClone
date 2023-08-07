package com.kanish.gnewscl.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kanish.gnewscl.data.repository.NewsFeedRepository

class FeedVmFactory(private val repository: NewsFeedRepository, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewsFeedViewModel::class.java)){
             NewsFeedViewModel(repository, application = application) as T
        }else{
            throw  java.lang.IllegalStateException("Could not create NewsViewModel")
        }

    }
}