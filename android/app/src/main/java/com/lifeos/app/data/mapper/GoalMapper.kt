package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.GoalEntity
import com.lifeos.domain.model.Goal

/**
 * Mapper functions for converting between GoalEntity and Goal domain model
 */

fun GoalEntity.toDomain(): Goal = Goal(
    id = id,
    userId = userId,
    whatDoYouWant = whatDoYouWant,
    folderId = folderId,
    notebookId = notebookId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Goal.toEntity(): GoalEntity = GoalEntity(
    id = id,
    userId = userId,
    whatDoYouWant = whatDoYouWant,
    folderId = folderId,
    notebookId = notebookId,
    createdAt = createdAt,
    updatedAt = updatedAt
)
