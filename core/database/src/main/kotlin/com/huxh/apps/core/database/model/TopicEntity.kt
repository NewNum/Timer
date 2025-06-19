package com.huxh.apps.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.huxh.apps.core.model.data.Topic

@Entity(
    tableName = "topics",
)
data class TopicEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val createTimestamp: Long,
    val updateTimestamp: Long,
)

fun TopicEntity.asExternalModel() = Topic(
    id = id,
    name = name,
    description = description,
    createTimestamp = createTimestamp,
    updateTimestamp = updateTimestamp,
)
