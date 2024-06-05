package com.loc.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import com.loc.newsapp.presentation.onboarding.OnboardingScreen
import com.loc.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Mandar as telas para trás da barra do sistema(verificar o arquivo themes.xml)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //Chamando a tela de splash
        installSplashScreen()

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect{
                Log.d("Test", it.toString())
            }
        }

        setContent {
            NewsAppTheme {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                    OnboardingScreen()
                }
            }
        }
    }
}
