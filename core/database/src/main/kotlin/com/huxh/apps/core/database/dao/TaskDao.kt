package com.huxh.apps.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.huxh.apps.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface  TaskDao {

    @Query(
        value = """
        SELECT * FROM tasks
        WHERE project_id = :projectId
    """,
    )
    fun getTaskEntity(projectId: Long): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTasks(topicEntities: List<TaskEntity>): List<Long>

    @Upsert
    suspend fun upsertTasks(entities: List<TaskEntity>)

    @Query(
        value = """
            DELETE FROM tasks
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteTasks(ids: List<String>)

}
