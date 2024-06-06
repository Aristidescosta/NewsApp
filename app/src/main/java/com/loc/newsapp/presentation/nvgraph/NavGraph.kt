package com.loc.newsapp.presentation.nvgraph

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.newsapp.presentation.onboarding.OnboardingScreen
import com.loc.newsapp.presentation.onboarding.OnboardingViewModel
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    startDestination: String,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(route = Route.OnBoardingScreen.route){
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ){
            val activity = "NavigationActivity"
            Log.d(activity, "ERRO NÃO IDENTIFICADO")
            composable(route = Route.NewsNavigationScreen.route){
                Text(text = "Rota principal")
            }
        }

    }
}