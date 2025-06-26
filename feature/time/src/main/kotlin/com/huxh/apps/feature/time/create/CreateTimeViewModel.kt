package com.huxh.apps.feature.time.create

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.huxh.apps.core.data.repository.ProjectRepository
import com.huxh.apps.core.data.repository.TaskRepository
import com.huxh.apps.core.database.model.ProjectEntity
import com.huxh.apps.core.database.model.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateTimeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val projectId = savedStateHandle.toRoute<CreateTimeRoute>().projectId

    init{
        viewModelScope.launch (Dispatchers.IO){
            projectRepository.getProject(projectId)?.let {entity->
                _title.update { entity.name }
                _desc.update { entity.description }
                _repeat.update { entity.repeatCount }
                val task = taskRepository.getTasks(projectId)
                list.addAll(task.map { it.duration })
            }
        }
    }

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false,
    )

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val title = _title.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = "",
    )


    private val _desc: MutableStateFlow<String> = MutableStateFlow("")
    val desc = _desc.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = "",
    )


    private val _repeat: MutableStateFlow<Int> = MutableStateFlow(1)
    val repeat = _repeat.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 1,
    )

    val list = mutableStateListOf<Long>()

    fun updateTitle(title: String) = _title.update { title }
    fun updateDesc(desc: String) = _desc.update { desc }
    fun updateRepeat(repeat: Int) = _repeat.update { if (repeat < 1) 1 else repeat }

    fun saveProject(
        navigationToTime: (Long) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val durations = list
            val title = _title.value
            val desc = _desc.value
            val repeatCount = _repeat.value
            val currentTimeMillis = System.currentTimeMillis()
            val duration = durations.sum()
            val defEntity = ProjectEntity(
                id = 0L,
                name = title,
                description = desc,
                duration = duration,
                repeatCount = repeatCount,
                createTimestamp = currentTimeMillis,
                updateTimestamp = currentTimeMillis,
            )
            val entity = if (projectId > 0L) {
                projectRepository.getProject(projectId) ?: defEntity
            } else {
                defEntity
            }.copy(
                name = title,
                description = desc,
                duration = duration,
                repeatCount = repeatCount,
                updateTimestamp = currentTimeMillis,
            )
            val id = if (projectId > 0L) {
                projectRepository.upsertProject(entity)
                projectId
            } else {
                projectRepository.insertOrIgnoreProject(entity)
            }
            taskRepository.deleteTaskByProjectId(id)
            taskRepository.insertTask(durations.mapIndexed { index, dur ->
                TaskEntity(0, id, dur, index)
            })
            withContext(Dispatchers.Main) { navigationToTime.invoke(id) }
        }

    }

}