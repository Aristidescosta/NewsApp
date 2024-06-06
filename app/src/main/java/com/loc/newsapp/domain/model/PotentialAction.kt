package com.loc.newsapp.domain.model

data class PotentialAction(
    val @type: String,
    val query-input: String,
    val target: List<String>
)