

package com.huxh.apps.core.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.huxh.apps.core.designsystem.icon.AppIcons
import com.huxh.apps.core.designsystem.theme.AppTheme


@Composable
fun RowScope.AppNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AppNavigationDefaults.navigationContentColor(),
            selectedTextColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AppNavigationDefaults.navigationContentColor(),
            indicatorColor = AppNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = AppNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun AppNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AppNavigationDefaults.navigationContentColor(),
            selectedTextColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AppNavigationDefaults.navigationContentColor(),
            indicatorColor = AppNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun AppNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = AppNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

@Composable
fun AppNavigationSuiteScaffold(
    navigationSuiteItems: AppNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AppNavigationDefaults.navigationContentColor(),
            selectedTextColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AppNavigationDefaults.navigationContentColor(),
            indicatorColor = AppNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AppNavigationDefaults.navigationContentColor(),
            selectedTextColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AppNavigationDefaults.navigationContentColor(),
            indicatorColor = AppNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AppNavigationDefaults.navigationContentColor(),
            selectedTextColor = AppNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AppNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = AppNavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

class AppNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
fun AppNavigationBarPreview() {
    val items = listOf("For you", "Saved", "Interests")
    val icons = listOf(
        AppIcons.UpcomingBorder,
        AppIcons.BookmarksBorder,
        AppIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        AppIcons.Upcoming,
        AppIcons.Bookmarks,
        AppIcons.Grid3x3,
    )

    AppTheme {
        AppNavigationBar {
            items.forEachIndexed { index, item ->
                AppNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun AppNavigationRailPreview() {
    val items = listOf("For you", "Saved", "Interests")
    val icons = listOf(
        AppIcons.UpcomingBorder,
        AppIcons.BookmarksBorder,
        AppIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        AppIcons.Upcoming,
        AppIcons.Bookmarks,
        AppIcons.Grid3x3,
    )

    AppTheme {
        AppNavigationRail {
            items.forEachIndexed { index, item ->
                AppNavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

object AppNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
