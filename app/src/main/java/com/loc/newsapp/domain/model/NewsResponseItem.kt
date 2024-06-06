package com.loc.newsapp.domain.model

data class NewsResponseItem(
    val author: Int,
    val categories: List<Int>,
    val content: Content,
    val date: String,
    val format: String,
    val guid: Guid,
    val id: Int,
    val link: String,
    val modified: String,
    val slug: String,
    val status: String,
    val tags: List<Any>,
    val title: Title,
    val yoast_head_json: YoastHeadJson
)