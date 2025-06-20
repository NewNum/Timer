package com.huxh.apps.core.database.di

import com.huxh.apps.core.database.AppDatabase
import com.huxh.apps.core.database.dao.ProjectDao
import com.huxh.apps.core.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesTaskDao(
        database: AppDatabase,
    ): TaskDao = database.taskDao()

    @Provides
    fun providesProjectDao(
        database: AppDatabase,
    ): ProjectDao = database.projectDao()

}
