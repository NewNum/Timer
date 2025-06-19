package com.huxh.apps.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.huxh.apps.core.database.model.TopicEntity
import kotlinx.coroutines.flow.Flow
@Dao

interface TopicDao {

    @Query(
        value = """
        SELECT * FROM topics
        WHERE id = :topicId
    """,
    )
    fun getTopicEntity(topicId: Long): Flow<TopicEntity>

}
