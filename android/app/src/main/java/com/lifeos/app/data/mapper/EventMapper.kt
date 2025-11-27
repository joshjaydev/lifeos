package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.EventEntity
import com.lifeos.domain.model.Event
import com.lifeos.domain.model.RecurrenceType

/**
 * Mapper functions for converting between EventEntity and Event domain model
 */

fun EventEntity.toDomain(): Event = Event(
    id = id,
    userId = userId,
    title = title,
    description = description,
    startDatetime = startDatetime,
    endDatetime = endDatetime,
    isRecurring = isRecurring,
    recurrenceType = RecurrenceType.fromString(recurrenceType),
    notifyBefore = notifyBefore,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Event.toEntity(): EventEntity = EventEntity(
    id = id,
    userId = userId,
    title = title,
    description = description,
    startDatetime = startDatetime,
    endDatetime = endDatetime,
    isRecurring = isRecurring,
    recurrenceType = recurrenceType?.toStorageString(),
    notifyBefore = notifyBefore,
    createdAt = createdAt,
    updatedAt = updatedAt
)
