package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        "Página 1",
        "Apresentação da página 1",
        R.drawable.menu_banner1
    ),
    Page(
        "Página 2",
        "Apresentação da página 2",
        R.drawable.menu_banner2
    ),
    Page(
        "Página 3",
        "Apresentação da página 3",
        R.drawable.menu_banner3
    ),

)
