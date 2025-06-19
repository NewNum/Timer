package com.huxh.apps.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
) {
    composable<HomeRoute> {
        HomeScreen()
    }
}
