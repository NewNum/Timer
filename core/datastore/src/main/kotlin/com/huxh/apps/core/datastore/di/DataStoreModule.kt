package com.huxh.apps.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.huxh.apps.core.datastore.AppPreferences
import com.huxh.apps.core.datastore.AppPreferencesSerializer
import com.huxh.apps.core.network.Dispatcher
import com.huxh.apps.core.network.AppDispatchers.IO
import com.huxh.apps.core.network.di.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesAppPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        appPreferencesSerializer: AppPreferencesSerializer,
    ): DataStore<AppPreferences> =
        DataStoreFactory.create(
            serializer = appPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
            migrations = listOf(),
        ) {
            context.dataStoreFile("app_preferences.pb")
        }
}
