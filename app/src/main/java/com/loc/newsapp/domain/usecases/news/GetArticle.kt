package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.Article

class GetArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(id: Int): Article?{
        return newsDao.getArticle(id = id)
    }

}