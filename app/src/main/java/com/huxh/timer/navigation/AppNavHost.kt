package com.huxh.timer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.huxh.apps.feature.home.HomeRoute
import com.huxh.apps.feature.home.homeScreen
import com.huxh.apps.feature.time.create.createTimeScreen
import com.huxh.apps.feature.time.create.navigateToCreateTime
import com.huxh.apps.feature.time.start.navigateToTime
import com.huxh.apps.feature.time.start.timeScreen
import com.huxh.timer.ui.AppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun AppNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        homeScreen(
            navigateToCreateTime = navController::navigateToCreateTime,
            navigateToTime = { navController.navigateToTime(it) },
        )
        createTimeScreen(
            navigateUp = navController::navigateUp,
            navigateToTime = {
                navController.navigateToTime(
                    it,
                ) {
                    popUpTo(HomeRoute) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
        )
        timeScreen(
            navigateUp = navController::navigateUp,
            navigateToCreateTime = navController::navigateToCreateTime,
        )
    }
}
