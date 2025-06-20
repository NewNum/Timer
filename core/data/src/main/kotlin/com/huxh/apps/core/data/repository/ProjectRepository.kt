package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getTopics(projectId: Long): Flow<List<TaskEntity>>

}
