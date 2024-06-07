package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.local.MockNewsPagingSource
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.MockRepository
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class MockNewsPagingSourceImpl() : MockRepository {
    override fun getMockPager(items: List<Article>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = items.size, enablePlaceholders = false),
            pagingSourceFactory = { MockNewsPagingSource(items) }
        ).flow
    }
}