package com.huxh.apps.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huxh.apps.core.database.dao.TopicDao
import com.huxh.apps.core.database.model.TopicEntity
import com.huxh.apps.core.database.util.InstantConverter

@Database(
    entities = [
        TopicEntity::class,
    ],
    version = 1,
    autoMigrations = [
    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
internal abstract class NiaDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
}
