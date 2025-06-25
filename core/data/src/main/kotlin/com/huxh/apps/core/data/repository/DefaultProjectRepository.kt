package com.huxh.apps.core.data.repository

import com.huxh.apps.core.database.dao.ProjectDao
import com.huxh.apps.core.database.dao.TaskDao
import com.huxh.apps.core.database.model.ProjectEntity
import com.huxh.apps.core.database.model.TaskEntity
import com.huxh.apps.core.datastore.AppPreferencesDataSource
import com.huxh.apps.core.model.data.AppConfigData
import com.huxh.apps.core.model.data.DarkThemeConfig
import com.huxh.apps.core.model.data.ThemeBrand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultProjectRepository @Inject constructor(
    private val projectDao: ProjectDao,
) : ProjectRepository {

    override fun getProject(projectId: Long): Flow<ProjectEntity?> {
        return projectDao.getProjectEntity(projectId)
    }

    override fun getAllProject(): Flow<List<ProjectEntity>> {
        return projectDao.getProjectEntity()
    }


}
