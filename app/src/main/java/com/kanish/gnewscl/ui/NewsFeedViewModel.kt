package com.kanish.gnewscl.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kanish.gnewscl.data.repository.NewsFeedRepository
import kotlinx.coroutines.launch

class NewsFeedViewModel(private  val repository: NewsFeedRepository,
                        application: Application):AndroidViewModel(application) {

    fun fetchNewsFeed(){
        viewModelScope.launch {
           val data = repository.fetchNewsFeed("entertainment")
            Log.d("kanish","data >>"+ data)
        }
    }
}