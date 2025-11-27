package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.FolderEntity
import com.lifeos.domain.model.Folder

/**
 * Mapper functions for converting between FolderEntity and Folder domain model
 */

fun FolderEntity.toDomain(): Folder = Folder(
    id = id,
    userId = userId,
    name = name,
    color = color,
    isDefault = isDefault,
    goalId = goalId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Folder.toEntity(): FolderEntity = FolderEntity(
    id = id,
    userId = userId,
    name = name,
    color = color,
    isDefault = isDefault,
    goalId = goalId,
    createdAt = createdAt,
    updatedAt = updatedAt
)
