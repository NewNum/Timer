

package com.huxh.apps.core.data.di

import com.huxh.apps.core.data.repository.OfflineFirstUserDataRepository
import com.huxh.apps.core.data.repository.UserDataRepository
import com.huxh.apps.core.data.util.ConnectivityManagerNetworkMonitor
import com.huxh.apps.core.data.util.NetworkMonitor
import com.huxh.apps.core.data.util.TimeZoneBroadcastMonitor
import com.huxh.apps.core.data.util.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    internal abstract fun binds(impl: TimeZoneBroadcastMonitor): TimeZoneMonitor
}
