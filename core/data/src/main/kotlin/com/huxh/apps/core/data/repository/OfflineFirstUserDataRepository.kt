package com.huxh.apps.core.data.repository

import com.huxh.apps.core.datastore.NiaPreferencesDataSource
import com.huxh.apps.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class OfflineFirstUserDataRepository @Inject constructor(
    private val niaPreferencesDataSource: NiaPreferencesDataSource,
) : UserDataRepository {

    override val userData: Flow<UserData> =
        niaPreferencesDataSource.userData

}
