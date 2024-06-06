package com.loc.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Content
import com.loc.newsapp.domain.model.NewsResponseItem

class NewsPagingSource(
    private val newsApi: NewsApi,
): PagingSource<Int, NewsResponseItem>() {

    var totalNewsCount = 0
    override fun getRefreshKey(state: PagingState<Int, NewsResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponseItem> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.content.rendered }
            LoadResult.Page(
                data = articles,
                nextKey = page + 1,
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