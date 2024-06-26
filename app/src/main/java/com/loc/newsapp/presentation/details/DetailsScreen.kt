package com.loc.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.details.components.DetailsTopBar
import com.loc.newsapp.presentation.home.FakeHomeViewModel
import com.loc.newsapp.presentation.onboarding.Dimens
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.util.UIComponent

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.yoast_head_json.og_image[0].url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.yoast_head_json.og_image[0].url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Dimens.MediumPadding1,
                end =Dimens.MediumPadding1,
                top =Dimens.MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.yoast_head_json.og_image[0].url)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
                Text(
                    text = article.yoast_head_json.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Text(
                    text = article.yoast_head_json.og_description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme(dynamicColor = false) {
        val newsViewModel = FakeHomeViewModel()
        val articles = newsViewModel.mockNews.collectAsLazyPagingItems()
        articles[0]?.let {
            DetailsScreen(
                article = it,
                event = {},
                sideEffect = null
            ) {

            }
        }
    }
}