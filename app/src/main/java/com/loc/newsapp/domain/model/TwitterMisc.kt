package com.loc.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class TwitterMisc(
    //@SerializedName("Written by") val writtenBy: String,
    @SerializedName("Est. reading time") val estimatedReadingTime: String
)
