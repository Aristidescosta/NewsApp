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
import com.loc.newsapp.domain.model.Content
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Guid
import com.loc.newsapp.domain.model.NewsResponseItem
import com.loc.newsapp.domain.model.OgImage
import com.loc.newsapp.domain.model.Robots
import com.loc.newsapp.domain.model.Title
import com.loc.newsapp.domain.model.TwitterMisc
import com.loc.newsapp.domain.model.YoastHeadJson
import com.loc.newsapp.presentation.onboarding.Dimens
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    article : NewsResponseItem,
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
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Text(
                text = article.title.rendered,
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
            article = NewsResponseItem(
                id =  15922,
                date = "2024-04-29T09:58:56",
                guid = Guid("https://blog.momenu.online/?p=15922"),
                modified = "2024-04-29T10:09:06",
                slug = "conde-dos-bolos-celebra-a-conquista-de-mais-um-feito-na-carreira",
                status = "publish",
                link = "https://blog.momenu.online/conde-dos-bolos-celebra-a-conquista-de-mais-um-feito-na-carreira/",
                title = Title("Conde dos Bolos celebra a conquista de mais um feito na carreira"),
                content = Content(protected = false, rendered = "<p><em><strong>O Cake Designer e empresário angolano, Conde dos Bolos, partilhou com o público neste segunda-feira, 30 de Maio, mais uma conquista profissional alcançada com a produção de bolos de casamento.</strong></em></p>\n<p style=\"text-align: justify;\">Apaixonado por moda e eventos, Conde dos Bolos já fez mais de dois mil bolos e contou ao AngoRussia, que tudo começou em 2009, com os pedidos especiais para casamentos, apesar de já prestar outros serviços da área.</p>\n<p style=\"text-align: justify;\">Com um total de 18 funcionários activos, o Cake Designer fez saber, que já produziu bolos de casamento para figuras do showbiz como, Kid MC, Helena Moreno, entre outros.</p>\n<p style=\"text-align: justify;\">Já nas redes sociais, o também actor partilhou com os seguidores a alegria de ter alcançado este feito na sua carreira profissional, e agradeceu em especial a todos os seus funcionários por sempre acreditarem no seu trabalho e dedicação.</p>\n<p style=\"text-align: justify;\">Além de Luanda, Conde dos Bolos também inaugurou a sua Confeitaria em Benguela – Lobito, criando 8 postos de trabalho local.</p>\n<p>Fonte: <a href=\"https://angorussia.com/lifestyle/conde-dos-bolos-celebra-a-conquista-de-mais-um-feito-na-carreira/\"><strong>AngoRussia</strong></a></p>\n"),
                author = 3,
                format = "standard",
                categories = listOf(75),
                tags = listOf(),
                yoast_head_json = YoastHeadJson(
                    title = "Conde dos Bolos celebra a conquista de mais um feito na carreira -",
                    robots = Robots(
                        index = "index" ,
                        follow = "follow",
                        maxSnippet = "max-snippet:-1",
                        maxImagePreview = "max-image-preview:large",
                        maxVideoPreview = "max-video-preview:-1"
                    ),
                    article_modified_time = "2024-04-29T10:09:06+00:00",
                    article_published_time = "2024-04-29T09:58:56+00:00",
                    author = "MoMenu",
                    canonical ="https://blog.momenu.online/conde-dos-bolos-celebra-a-conquista-de-mais-um-feito-na-carreira/" ,
                    og_description = "O Cake Designer e empresário angolano, Conde dos Bolos, partilhou com o público neste segunda-feira, 30 de Maio, mais uma",
                    og_image = listOf(
                        OgImage(
                            width = 1080,
                            height = 1080,
                            type = "image/jpeg",
                            url = "https://blog.momenu.online/wp-content/uploads/2024/04/WhatsApp-Image-2021-11-18-at-13.09.21-1.jpeg"
                        )
                    ),
                    og_locale = "pt_PT",
                    og_title = "Conde dos Bolos celebra a conquista de mais um feito na carreira -",
                    og_type = "article",
                    og_url = "https://blog.momenu.online/conde-dos-bolos-celebra-a-conquista-de-mais-um-feito-na-carreira/",
                    twitter_misc = TwitterMisc(
                        estimatedReadingTime = "2 minutos"
                    )
                )
            ),
            onClick = {  })
    }
}