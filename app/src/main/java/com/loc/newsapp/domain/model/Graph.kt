package com.loc.newsapp.domain.model

data class Graph(
    val @id: String,
    val @type: String,
    val author: AuthorX,
    val breadcrumb: Breadcrumb,
    val contentUrl: String,
    val dateModified: String,
    val datePublished: String,
    val description: String,
    val height: Int,
    val image: Image,
    val inLanguage: String,
    val isPartOf: IsPartOf,
    val itemListElement: List<ItemElement>,
    val name: String,
    val potentialAction: List<PotentialAction>,
    val primaryImageOfPage: PrimaryImageOfPage,
    val sameAs: List<String>,
    val thumbnailUrl: String,
    val url: String,
    val width: Int
)