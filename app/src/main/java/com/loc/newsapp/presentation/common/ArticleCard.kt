package com.loc.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.OgImage
import com.loc.newsapp.domain.model.TwitterMisc
import com.loc.newsapp.domain.model.YoastHeadJson
import com.loc.newsapp.presentation.onboarding.Dimens
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    article : Article,
    onClick: () -> Unit,

    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row (
        modifier = modifier.clickable { onClick() }
    ){
        AsyncImage(
            model = ImageRequest.Builder(context).data(article.yoast_head_json.og_image[0].url).build(),
            contentDescription = null,
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(Dimens.MediumPadding1))

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Text(
                text = article.yoast_head_json.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.yoast_head_json.author,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))

                Text(
                    text = article.yoast_head_json.twitter_misc.estimatedReadingTime,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                id =  15922,

                yoast_head_json = YoastHeadJson(
                    title = "Conde dos Bolos celebra a conquista de mais um feito na carreira -",
                    author = "MoMenu",
                    og_description = "O Cake Designer e empresário angolano, Conde dos Bolos, partilhou com o público neste segunda-feira, 30 de Maio, mais uma",
                    og_image = listOf(
                        OgImage(
                            url = "https://blog.momenu.online/wp-content/uploads/2024/04/WhatsApp-Image-2021-11-18-at-13.09.21-1.jpeg"
                        )
                    ),
                    twitter_misc = TwitterMisc(
                        estimatedReadingTime = "2 minutos"
                    )
                )
            ),
            onClick = {  })
    }
}