package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.OgImage
import com.loc.newsapp.domain.model.TwitterMisc
import com.loc.newsapp.domain.model.YoastHeadJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeHomeViewModel : ViewModel() {
    private val _mockNews = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val mockNews: StateFlow<PagingData<Article>> = _mockNews

    init {
        // Adicione alguns dados falsos para a pré-visualização
        val image = R.drawable.logo_mom
        val article = Article(
            id =  15922,
            yoast_head_json = YoastHeadJson(
                title = "Conde dos Bolos celebra a conquista de mais um feito na carreira -",
                og_description = "O Cake Designer e empresário angolano, Conde dos Bolos, partilhou com o público neste segunda-feira, 30 de Maio, mais uma",
                og_image = listOf(
                    OgImage(
                        url = image.toString()
                    )
                ),
                author = "Momenu",
                twitter_misc = TwitterMisc(
                    estimatedReadingTime = "2 minutos"
                )
            )
        )
        val fakeArticles = listOf(article, article, article)
        _mockNews.value = PagingData.from(fakeArticles)
    }
}