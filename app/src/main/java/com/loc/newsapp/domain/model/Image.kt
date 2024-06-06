package com.loc.newsapp.domain.model

data class Image(
    val @id: String,
    val @type: String,
    val caption: String,
    val contentUrl: String,
    val inLanguage: String,
    val url: String
)