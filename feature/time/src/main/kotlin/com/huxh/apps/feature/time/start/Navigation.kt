package com.huxh.apps.feature.time.start

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class TimeRoute(val projectId: Long = 0)

fun NavController.navigateToTime(
    projectId: Long = 0,
    builder: NavOptionsBuilder.() -> Unit = {}
) =
    navigate(route = TimeRoute(projectId), builder)

fun NavGraphBuilder.timeScreen(
    navigateUp: () -> Unit,
    navigateToCreateTime: (Long) -> Unit
) {
    composable<TimeRoute> {
        TimeScreen(
            navigateUp = navigateUp,
            navigateToCreateTime = navigateToCreateTime,
        )
    }
}
