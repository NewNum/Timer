package com.huxh.apps.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "tasks",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class TaskEntity(
    val id: Long,
    @ColumnInfo(name = "project_id")
    val projectId: Long,
    @ColumnInfo(defaultValue = "")
    val duration: Long,
    @ColumnInfo(name = "sort_order", defaultValue = "0")
    val sortOrder: Int,
)
