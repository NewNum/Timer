package com.huxh.apps.core.data.repository

import com.huxh.apps.core.datastore.AppPreferencesDataSource
import com.huxh.apps.core.model.data.AppConfigData
import com.huxh.apps.core.model.data.DarkThemeConfig
import com.huxh.apps.core.model.data.ThemeBrand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultAppConfigRepository @Inject constructor(
    private val appPreferencesDataSource: AppPreferencesDataSource,
) : AppConfigRepository {

    override val appConfigData: Flow<AppConfigData> = appPreferencesDataSource.appConfigData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        appPreferencesDataSource.setThemeBrand(themeBrand)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        appPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        appPreferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }

}
