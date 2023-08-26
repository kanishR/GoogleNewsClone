package com.kanish.gnewscl.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanish.gnewscl.NewsApplication
import com.kanish.gnewscl.R
import com.kanish.gnewscl.data.RetrofitClient
import com.kanish.gnewscl.data.network.FeedNetworkService
import com.kanish.gnewscl.data.repository.NewsFeedRepository
import com.kanish.gnewscl.domain.NewsFeedScreenState

class NewsFeedFragment:Fragment() {
    private val newsFeedViewModel : NewsFeedViewModel by lazy {
        val apiService= RetrofitClient.apiService
        val networkService= FeedNetworkService(apiService)
        val newsRepository = NewsFeedRepository(networkService,NewsApplication.newsDatabase.newsDao())
        ViewModelProvider(this, factory = FeedVmFactory(newsRepository, requireActivity().application ))[NewsFeedViewModel::class.java]

    }
    private lateinit var recyclerView: RecyclerView
    private val newsListAdapter:NewsListAdapter by lazy {
        NewsListAdapter(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rvNewsFeed)
        newsFeedViewModel.fetchNewsFeed()
        setUpRecycler()
        setUpObservers()

    }

    private fun setUpRecycler() {
        val lmanager= LinearLayoutManager(context)
      recyclerView.layoutManager= lmanager
        recyclerView.adapter=newsListAdapter
        Log.d("kanish","news List Size"+newsListAdapter.itemList.size)
        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleCount= lmanager.childCount
                val totalItemCount = lmanager.itemCount
                val firstVisibleItem =lmanager.findFirstVisibleItemPosition()

                Log.d("kanish","visibleCount"+visibleCount+ "totalItemCount>>"+totalItemCount +"firstVisibleItem"+firstVisibleItem)
                if ((firstVisibleItem+visibleCount)>=totalItemCount-5){
                    Log.d("kanish","Load more data call")
                    loadMoreData()
                }
            }
        })
    }

    private fun loadMoreData() {
        newsFeedViewModel.fetchNewsFeed()
    }

    private fun setUpObservers() {
        newsFeedViewModel.mNewsFeedState.observe(viewLifecycleOwner, Observer {
            when(it){
                NewsFeedScreenState.FeedError -> Log.d("kanish","some Errror")
                NewsFeedScreenState.Loading ->  Log.d("kanish","loading")
                is NewsFeedScreenState.NewsFeedAvailable -> {
                    newsListAdapter.itemList.addAll(it.result)
                    Log.d("kanish","news List Size"+newsListAdapter.itemList.size)
                    newsListAdapter.notifyDataSetChanged()

                }
            }
        })
    }
}