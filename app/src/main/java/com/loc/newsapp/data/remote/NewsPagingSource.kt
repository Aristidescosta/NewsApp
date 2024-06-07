package com.loc.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
): PagingSource<Int, Article>() {

    var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        Log.d("PAGE", page.toString())
        return try {
            val newsResponse = newsApi.getNews(page)
            totalNewsCount += newsResponse.size
            val articles = newsResponse.distinctBy { it.id }
            LoadResult.Page(
                data = articles,
                nextKey = null,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}