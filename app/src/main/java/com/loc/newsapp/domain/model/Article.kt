package com.loc.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey val id: Int,
    val yoast_head_json: YoastHeadJson
)
