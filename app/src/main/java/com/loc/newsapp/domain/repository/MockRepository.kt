package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface MockRepository {
    fun getMockPager(items: List<Article>): Flow<PagingData<Article>>
}