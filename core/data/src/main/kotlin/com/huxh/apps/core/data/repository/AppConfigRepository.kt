package com.huxh.apps.core.data.repository

import com.huxh.apps.core.model.data.AppConfigData
import com.huxh.apps.core.model.data.DarkThemeConfig
import com.huxh.apps.core.model.data.ThemeBrand
import kotlinx.coroutines.flow.Flow

interface AppConfigRepository {

    val appConfigData: Flow<AppConfigData>

    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)

}
