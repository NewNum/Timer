package com.huxh.apps.feature.time.start

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val projectId = savedStateHandle.toRoute<TimeRoute>().projectId
}