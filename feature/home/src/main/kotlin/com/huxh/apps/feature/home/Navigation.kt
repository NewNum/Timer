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
    navigateToCreateTime: () -> Unit,
    navigateToTime: (Long) -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            navigateToCreateTime = navigateToCreateTime,
            navigateToTime = navigateToTime,
        )
    }
}
