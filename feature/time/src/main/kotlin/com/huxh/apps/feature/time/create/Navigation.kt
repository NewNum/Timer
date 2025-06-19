package com.huxh.apps.feature.time.create

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
private data object CreateTimeRoute

fun NavController.navigateToCreateTime(navOptions: NavOptions? = null) =
    navigate(route = CreateTimeRoute, navOptions)

fun NavGraphBuilder.createTimeScreen(
    navigateUp: () -> Unit,
) {
    composable<CreateTimeRoute> {
        CreateTimeScreen(navigateUp)
    }
}
