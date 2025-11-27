package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.ActionEntity
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.RecurrenceType

/**
 * Mapper functions for converting between ActionEntity and Action domain model
 */

fun ActionEntity.toDomain(): Action = Action(
    id = id,
    userId = userId,
    folderId = folderId,
    title = title,
    dueDate = dueDate,
    dueTime = dueTime,
    isRecurring = isRecurring,
    recurrenceType = RecurrenceType.fromString(recurrenceType),
    notifyBefore = notifyBefore,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Action.toEntity(): ActionEntity = ActionEntity(
    id = id,
    userId = userId,
    folderId = folderId,
    title = title,
    dueDate = dueDate,
    dueTime = dueTime,
    isRecurring = isRecurring,
    recurrenceType = recurrenceType?.toStorageString(),
    notifyBefore = notifyBefore,
    createdAt = createdAt,
    updatedAt = updatedAt
)
