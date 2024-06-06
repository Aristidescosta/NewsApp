package com.loc.newsapp.data.remote

import com.loc.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET

interface NewsApi {
    @GET("posts")
    suspend fun getNews(): NewsResponse
}