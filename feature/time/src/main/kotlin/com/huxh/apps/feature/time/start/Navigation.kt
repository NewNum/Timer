package com.huxh.apps.feature.time.start

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class TimeRoute(val projectId: Long = 0)

fun NavController.navigateToTime(projectId: Long = 0, navOptions: NavOptions? = null) =
    navigate(route = TimeRoute(projectId), navOptions)

fun NavGraphBuilder.timeScreen(
    navigateUp: () -> Unit,
) {
    composable<TimeRoute> {
        TimeScreen(navigateUp)
    }
}
