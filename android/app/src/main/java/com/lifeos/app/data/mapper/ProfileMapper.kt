package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.ProfileEntity
import com.lifeos.domain.model.Profile

/**
 * Mapper functions for converting between ProfileEntity and Profile domain model
 */

fun ProfileEntity.toDomain(): Profile = Profile(
    id = id,
    email = email,
    displayName = displayName,
    avatarUrl = avatarUrl,
    timeBlockSize = timeBlockSize,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Profile.toEntity(): ProfileEntity = ProfileEntity(
    id = id,
    email = email,
    displayName = displayName,
    avatarUrl = avatarUrl,
    timeBlockSize = timeBlockSize,
    createdAt = createdAt,
    updatedAt = updatedAt
)
