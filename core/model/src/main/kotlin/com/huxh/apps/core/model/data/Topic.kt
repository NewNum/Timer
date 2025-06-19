

package com.huxh.apps.core.model.data

/**
 * External data layer representation of a NiA Topic
 */
data class Topic(
    val id: Long,
    val name: String,
    val description: String,
    val createTimestamp: Long,
    val updateTimestamp: Long,
)
