package com.huxh.apps.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.huxh.apps.core.database.model.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query(
        value = """
        SELECT * FROM projects
        WHERE id = :projectId
    """,
    )
    fun getProjectEntityFlow(projectId: Long): Flow<ProjectEntity?>

    @Query(
        value = """
        SELECT * FROM projects
        WHERE id = :projectId
    """,
    )
   suspend fun getProjectEntity(projectId: Long): ProjectEntity?

    @Query(
        value = """
        SELECT * FROM projects
    """,
    )
    fun getProjectEntity(): Flow<List<ProjectEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreProjects(topicEntities: List<ProjectEntity>): List<Long>

    @Upsert
    suspend fun upsertProjects(entities: List<ProjectEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreProject(projectEntity: ProjectEntity): Long

    @Upsert
    suspend fun upsertProject(entity: ProjectEntity): Long

    @Query(
        value = """
            DELETE FROM projects
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteProjects(ids: List<String>)

}
