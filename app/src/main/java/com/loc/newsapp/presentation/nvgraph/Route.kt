package com.loc.newsapp.presentation.nvgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScrenn")
    object HomeScreen : Route(route = "homeScrenn")
    object SearchScreen : Route(route = "searchScrenn")
    object BookmarkScreen : Route(route = "bookmarkScrenn")
    object DetailsScreen : Route(route = "detailsScrenn")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "NewsNavigation")
    object NewsNavigationScreen : Route(route = "NewsNavigationScreen")
}