package com.loc.newsapp.presentation.onboarding

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.newsapp.presentation.onboarding.common.NewsButton
import com.loc.newsapp.presentation.onboarding.common.NewsTextButton
import com.loc.newsapp.presentation.onboarding.components.OnboardingPage
import com.loc.newsapp.presentation.onboarding.components.PageIndicator
import com.loc.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    event: (OnBoardingEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage){
                0 -> listOf("", "Próximo")
                1 -> listOf("Voltar", "Próximo")
                2 -> listOf("Voltar", "Iniciar")
                else -> listOf("", "")
            }
        }
    }
    Log.d("OnboardingScreen", "DÁ ERRO NÃO, POR FAVOR")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding2)
                .navigationBarsPadding(), //calcular o preenchimento da barra de navegação
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
                modifier = Modifier
                    .width(Dimens.PageIndicatorWidth),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()
                if(buttonState.value[0].isNotEmpty()){
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if(pagerState.currentPage == 2){
                                event(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    NewsAppTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(event = viewModel::onEvent)
        }
    }
}