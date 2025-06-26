package com.huxh.apps.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "project_id")
    val projectId: Long,
    @ColumnInfo(defaultValue = "")
    val duration: Long,
    @ColumnInfo(name = "sort_order", defaultValue = "0")
    val sortOrder: Int,
)
