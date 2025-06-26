package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.dao.ProjectDao
import com.huxh.apps.core.database.model.ProjectEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultProjectRepository @Inject constructor(
    private val projectDao: ProjectDao,
) : ProjectRepository {

    override fun getProjectFlow(projectId: Long): Flow<ProjectEntity?> {
        return projectDao.getProjectEntityFlow(projectId)
    }

    override suspend fun getProject(projectId: Long): ProjectEntity? {
        return projectDao.getProjectEntity(projectId)
    }

    override fun getAllProject(): Flow<List<ProjectEntity>> {
        return projectDao.getProjectEntity()
    }

    override suspend fun insertOrIgnoreProject(projectEntity: ProjectEntity): Long {
        return projectDao.insertOrIgnoreProject(projectEntity)
    }

    override suspend fun upsertProject(entity: ProjectEntity): Long {
        return projectDao.upsertProject(entity)
    }


}
