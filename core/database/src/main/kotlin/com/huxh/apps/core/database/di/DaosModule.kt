

package com.huxh.apps.core.database.di

import com.huxh.apps.core.database.AppDatabase
import com.huxh.apps.core.database.dao.TopicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: AppDatabase,
    ): TopicDao = database.topicDao()

}
