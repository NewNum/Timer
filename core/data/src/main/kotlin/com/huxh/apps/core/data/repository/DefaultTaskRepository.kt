package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.dao.TaskDao
import com.huxh.apps.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultTaskRepository @Inject constructor(
    private val taskDao: TaskDao,
) : TaskRepository {
    override suspend fun getTasks(projectId: Long): List<TaskEntity> {
        return taskDao.getTaskEntity(projectId)
    }

    override fun getTasksFlow(projectId: Long): Flow<List<TaskEntity>> {
        return taskDao.getTaskEntityFlow(projectId)
    }

}
