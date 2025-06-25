package com.huxh.apps.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huxh.apps.core.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    projectRepository: ProjectRepository,
) : ViewModel() {

    val projects = projectRepository.getAllProject().stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5_000),
    )

}