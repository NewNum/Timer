package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.model.ProjectEntity
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getProjectFlow(projectId: Long): Flow<ProjectEntity?>

    suspend fun getProject(projectId: Long): ProjectEntity?

    fun getAllProject(): Flow<List<ProjectEntity>>

    suspend fun insertOrIgnoreProject(projectEntity: ProjectEntity): Long

    suspend fun upsertProject(entity: ProjectEntity): Long


}
