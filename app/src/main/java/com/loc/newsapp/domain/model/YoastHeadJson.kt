package com.loc.newsapp.domain.model

data class YoastHeadJson(
    val title: String,
    val author: String,
    val og_description: String,
    val og_image: List<OgImage>,
    val twitter_misc: TwitterMisc
)