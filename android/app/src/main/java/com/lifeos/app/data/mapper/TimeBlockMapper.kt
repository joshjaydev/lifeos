package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.TimeBlockEntity
import com.lifeos.domain.model.TimeBlock

/**
 * Mapper functions for converting between TimeBlockEntity and TimeBlock domain model
 */

fun TimeBlockEntity.toDomain(): TimeBlock = TimeBlock(
    id = id,
    userId = userId,
    actionId = actionId,
    blockDate = blockDate,
    startTime = startTime,
    endTime = endTime,
    createdAt = createdAt
)

fun TimeBlock.toEntity(): TimeBlockEntity = TimeBlockEntity(
    id = id,
    userId = userId,
    actionId = actionId,
    blockDate = blockDate,
    startTime = startTime,
    endTime = endTime,
    createdAt = createdAt
)
