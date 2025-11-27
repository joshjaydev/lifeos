package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.NotebookEntity
import com.lifeos.domain.model.Notebook

/**
 * Mapper functions for converting between NotebookEntity and Notebook domain model
 */

fun NotebookEntity.toDomain(): Notebook = Notebook(
    id = id,
    userId = userId,
    name = name,
    isDefault = isDefault,
    goalId = goalId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Notebook.toEntity(): NotebookEntity = NotebookEntity(
    id = id,
    userId = userId,
    name = name,
    isDefault = isDefault,
    goalId = goalId,
    createdAt = createdAt,
    updatedAt = updatedAt
)
