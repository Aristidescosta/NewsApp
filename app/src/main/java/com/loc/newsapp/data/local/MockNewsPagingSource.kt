package com.loc.newsapp.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article

class MockNewsPagingSource (
    private val items: List<Article>
): PagingSource<Int, Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


}