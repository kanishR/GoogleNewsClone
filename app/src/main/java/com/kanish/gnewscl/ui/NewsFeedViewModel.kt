package com.kanish.gnewscl.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kanish.gnewscl.data.repository.NewsFeedRepository
import com.kanish.gnewscl.domain.NewsFeedScreenState
import kotlinx.coroutines.launch

class NewsFeedViewModel(private  val repository: NewsFeedRepository,
                        application: Application):AndroidViewModel(application) {
    val mNewsFeedState: MutableLiveData<NewsFeedScreenState> = MutableLiveData()

    fun fetchNewsFeed(){
        viewModelScope.launch {
            setViewState(NewsFeedScreenState.Loading)
           val data = repository.fetchNewsFeed("entertainment")
            setViewState(NewsFeedScreenState.NewsFeedAvailable(data))
        }
    }
    fun setViewState(state:NewsFeedScreenState){
        mNewsFeedState.value = state
    }
}