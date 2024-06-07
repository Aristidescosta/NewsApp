package com.loc.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.OgImage
import com.loc.newsapp.domain.model.TwitterMisc
import com.loc.newsapp.domain.model.YoastHeadJson
import com.loc.newsapp.domain.repository.MockRepository
import kotlinx.coroutines.flow.Flow

class GetMockNews(
    private val newsRepository: MockRepository
) {
    val items = listOf(Article(
        id =  15922,
        yoast_head_json = YoastHeadJson(
            title = "Conde dos Bolos celebra a conquista de mais um feito na carreira -",
            og_description = "O Cake Designer e empresário angolano, Conde dos Bolos, partilhou com o público neste segunda-feira, 30 de Maio, mais uma",
            og_image = listOf(
                OgImage(
                    url = "https://blog.momenu.online/wp-content/uploads/2024/04/WhatsApp-Image-2021-11-18-at-13.09.21-1.jpeg"
                )
            ),
            author = "Momenu",
            twitter_misc = TwitterMisc(
                estimatedReadingTime = "2 minutos"
            )
        )
    ))
    operator fun invoke(): Flow<PagingData<Article>> {
        return newsRepository.getMockPager(items = items)
    }
}