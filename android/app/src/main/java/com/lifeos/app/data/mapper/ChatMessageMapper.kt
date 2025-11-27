package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.ChatMessageEntity
import com.lifeos.domain.model.ChatMessage
import com.lifeos.domain.model.MessageRole

/**
 * Mapper functions for converting between ChatMessageEntity and ChatMessage domain model
 */

fun ChatMessageEntity.toDomain(): ChatMessage = ChatMessage(
    id = id,
    userId = userId,
    role = MessageRole.fromString(role),
    content = content,
    createdAt = createdAt
)

fun ChatMessage.toEntity(): ChatMessageEntity = ChatMessageEntity(
    id = id,
    userId = userId,
    role = role.toStorageString(),
    content = content,
    createdAt = createdAt
)
