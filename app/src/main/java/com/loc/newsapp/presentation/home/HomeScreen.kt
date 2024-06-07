package com.loc.newsapp.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.nvgraph.Route
import com.loc.newsapp.presentation.onboarding.Dimens
import com.loc.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (Article) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 "){ it.yoast_head_json.title }
            } else{
                ""
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_mom),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Dimens.MediumPadding1)
        )
        
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {  },
            onClick = {
             //TODO
            }
        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        
        Text(
            text = titles,
            modifier = Modifier
                .height(Dimens.MediumPadding1)
                .fillMaxWidth()
                .basicMarquee(),
            fontSize = 16.sp,
            color = colorResource(id = R.color.placeholder)
        )

        val pagerState = rememberPagerState (
            pageCount = { 5 }
        )

        val coroutineSCope = rememberCoroutineScope()

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
            ,
            contentColor = colorResource(id = R.color.primary_color),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = colorResource(id = R.color.primary_color),
                    height = 2.dp,
                    modifier =  Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            val tabs = listOf(
                Pair(0, "Novos"),
                Pair(1, "Restaurantes"),
                Pair(2, "Antigos")
            )

            tabs.forEach {
                Tab(
                    selected = pagerState.currentPage == it.first,
                    onClick = {
                        coroutineSCope.launch {
                            pagerState.scrollToPage(it.first) }
                    },
                    text = { Text(text = it.second) }
                )
            }
        }

        HorizontalPager(
            state = pagerState
        ) {
            when(it){
                0 -> ArticlesList(
                    articles = articles
                ) { navigate(it) }

                else -> Text(text = "Olá da página $it")
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    NewsAppTheme {
        val newsViewModel = FakeHomeViewModel()
        val articles = newsViewModel.mockNews.collectAsLazyPagingItems()
        HomeScreen(articles = articles, navigate = {})
    }
}