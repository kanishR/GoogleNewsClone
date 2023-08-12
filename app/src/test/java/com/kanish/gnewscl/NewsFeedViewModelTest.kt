package com.kanish.gnewscl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kanish.gnewscl.data.ApiService
import com.kanish.gnewscl.data.entity.NewsResult
import com.kanish.gnewscl.data.network.FeedNetworkService
import com.kanish.gnewscl.data.repository.NewsFeedRepository
import com.kanish.gnewscl.domain.NewsFeedScreenState
import com.kanish.gnewscl.ui.NewsFeedViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class NewsFeedViewModelTest {

    // Can be moved to base class
    val testCoroutineDispatcher = TestCoroutineDispatcher()
    val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule(testCoroutineDispatcher, testCoroutineScope)

    private val mockApiService = mock(ApiService::class.java)
    private val mockFeedService = FeedNetworkService(mockApiService)
    private val repository = spy(NewsFeedRepository(mockFeedService))
    private val viewModel: NewsFeedViewModel = spy(NewsFeedViewModel(
        repository,
        ApplicationProvider.getApplicationContext()
    ))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchData_whenSuccess_returnData() {

        testCoroutineRule.runBlockingTest {
            val mockResponse: List<NewsResult> = listOf<NewsResult>(NewsResult(listOf(), "", listOf(), listOf(), "", "", listOf(), "", "", "", "", "", ""))
            Mockito.doReturn(mockResponse).`when`(repository).fetchNewsFeed("entertainment")

            viewModel.fetchNewsFeed()

            assertTrue(viewModel.mNewsFeedState.value is NewsFeedScreenState.NewsFeedAvailable);
        }

    }

    @Test
    fun fetchData_whenFailure_returnEmptyList() {
        runTest {
            val mockResponse: List<NewsResult> = listOf<NewsResult>()
            Mockito.doReturn(mockResponse).`when`(repository).fetchNewsFeed("entertainment")

            viewModel.fetchNewsFeed()

            assertEquals(viewModel.mNewsFeedState.value, NewsFeedScreenState.NewsFeedAvailable(mockResponse));
        }

    }
}