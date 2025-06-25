package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.model.ProjectEntity
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getProject(projectId: Long): Flow<ProjectEntity?>

    fun getAllProject(): Flow<List<ProjectEntity>>

}
