package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getTasks(projectId: Long): List<TaskEntity>

    fun getTasksFlow(projectId: Long): Flow<List<TaskEntity>>

}
