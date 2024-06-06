package com.loc.newsapp.data.remote

import com.loc.newsapp.domain.model.NewsResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("posts")
    suspend fun getNews(@Query("page") page: Int): List<NewsResponseItem>
}