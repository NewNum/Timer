package com.huxh.apps.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "projects",
)
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val duration: Long,
    @ColumnInfo(name = "repeat_count")
    val repeatCount: Int,
    @ColumnInfo(name = "create_timestamp")
    val createTimestamp: Long,
    @ColumnInfo(name = "update_timestamp")
    val updateTimestamp: Long,
)
