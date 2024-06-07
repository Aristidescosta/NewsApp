package com.loc.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)
data class NewsMockUseCases(
    val getMockNews: GetMockNews
)
