package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.domain.model.NewsResponseItem
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {
    val news = newsUseCases.getNews().cachedIn(viewModelScope)

    private val _mockedArticles = MutableStateFlow(flowOf(PagingData.empty<NewsResponseItem>()))
    val mockedArticles = _mockedArticles

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            val fetchedPosts = newsUseCases.getNews()
            _mockedArticles.value = fetchedPosts
        }
    }
}