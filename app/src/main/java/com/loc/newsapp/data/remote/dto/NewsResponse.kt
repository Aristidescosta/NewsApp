package com.loc.newsapp.data.remote.dto

import com.loc.newsapp.domain.model.NewsResponseItem

data class NewsResponse(
    val articles: List<NewsResponseItem>
)