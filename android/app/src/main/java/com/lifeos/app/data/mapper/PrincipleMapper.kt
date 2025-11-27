package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.PrincipleEntity
import com.lifeos.domain.model.Principle

/**
 * Mapper functions for converting between PrincipleEntity and Principle domain model
 */

fun PrincipleEntity.toDomain(): Principle = Principle(
    id = id,
    userId = userId,
    parentId = parentId,
    fundamentalTruth = fundamentalTruth,
    experience = experience,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Principle.toEntity(): PrincipleEntity = PrincipleEntity(
    id = id,
    userId = userId,
    parentId = parentId,
    fundamentalTruth = fundamentalTruth,
    experience = experience,
    createdAt = createdAt,
    updatedAt = updatedAt
)
