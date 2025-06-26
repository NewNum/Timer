package com.huxh.apps.feature.time.create

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class CreateTimeRoute(val projectId: Long = 0L)

fun NavController.navigateToCreateTime(
    projectId: Long = 0L,
    navOptions: NavOptions? = null,
) =
    navigate(route = CreateTimeRoute(projectId), navOptions)

fun NavGraphBuilder.createTimeScreen(
    navigateUp: () -> Unit,
    navigateToTime: (Long) -> Unit,
) {
    composable<CreateTimeRoute> {
        CreateTimeScreen(
            navigateUp = navigateUp,
            navigateToTime = navigateToTime,
        )
    }
}
