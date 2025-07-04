package com.huxh.apps.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huxh.apps.core.database.dao.ProjectDao
import com.huxh.apps.core.database.dao.TaskDao
import com.huxh.apps.core.database.model.ProjectEntity
import com.huxh.apps.core.database.model.TaskEntity
import com.huxh.apps.core.database.util.InstantConverter

@Database(
    entities = [
        ProjectEntity::class,
        TaskEntity::class,
    ],
    version = 1,
    autoMigrations = [
    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    abstract fun taskDao(): TaskDao

}
