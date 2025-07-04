package com.huxh.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huxh.apps.core.data.repository.AppConfigRepository
import com.huxh.apps.core.model.data.DarkThemeConfig
import com.huxh.apps.core.model.data.ThemeBrand
import com.huxh.apps.core.model.data.AppConfigData
import com.huxh.timer.MainActivityUiState.Loading
import com.huxh.timer.MainActivityUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appConfigRepository: AppConfigRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = appConfigRepository.appConfigData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    data class Success(val appConfigData: AppConfigData) : MainActivityUiState {
        override val shouldDisableDynamicTheming = !appConfigData.useDynamicColor

        override val shouldUseAndroidTheme: Boolean = when (appConfigData.themeBrand) {
            ThemeBrand.DEFAULT -> false
            ThemeBrand.ANDROID -> true
        }

        override fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) =
            when (appConfigData.darkThemeConfig) {
                DarkThemeConfig.FOLLOW_SYSTEM -> isSystemDarkTheme
                DarkThemeConfig.LIGHT -> false
                DarkThemeConfig.DARK -> true
            }
    }

    /**
     * Returns `true` if the state wasn't loaded yet and it should keep showing the splash screen.
     */
    fun shouldKeepSplashScreen() = this is Loading

    /**
     * Returns `true` if the dynamic color is disabled.
     */
    val shouldDisableDynamicTheming: Boolean get() = true

    /**
     * Returns `true` if the Android theme should be used.
     */
    val shouldUseAndroidTheme: Boolean get() = false

    /**
     * Returns `true` if dark theme should be used.
     */
    fun shouldUseDarkTheme(isSystemDarkTheme: Boolean) = isSystemDarkTheme
}
