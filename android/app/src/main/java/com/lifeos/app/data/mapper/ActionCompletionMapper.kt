package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.ActionCompletionEntity
import com.lifeos.domain.model.ActionCompletion

/**
 * Mapper functions for converting between ActionCompletionEntity and ActionCompletion domain model
 */

fun ActionCompletionEntity.toDomain(): ActionCompletion = ActionCompletion(
    id = id,
    actionId = actionId,
    completedDate = completedDate,
    completedAt = completedAt
)

fun ActionCompletion.toEntity(): ActionCompletionEntity = ActionCompletionEntity(
    id = id,
    actionId = actionId,
    completedDate = completedDate,
    completedAt = completedAt
)
