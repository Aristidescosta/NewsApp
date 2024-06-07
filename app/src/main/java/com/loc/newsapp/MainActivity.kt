package com.loc.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.OgImage
import com.loc.newsapp.domain.model.TwitterMisc
import com.loc.newsapp.domain.model.YoastHeadJson
import com.loc.newsapp.presentation.nvgraph.NavGraph
import com.loc.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Mandar as telas para trás da barra do sistema(verificar o arquivo themes.xml)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //Chamando a tela de splash
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }

        setContent {
            NewsAppTheme {

                //Removendo a cor da barra do sistema
                val isSystemDarkMode = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDarkMode
                    )
                }

                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                    val startDestination = viewModel.starDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
