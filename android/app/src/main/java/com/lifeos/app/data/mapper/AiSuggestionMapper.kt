package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.AiSuggestionEntity
import com.lifeos.domain.model.AiSuggestion
import com.lifeos.domain.model.SuggestionStatus
import com.lifeos.domain.model.SuggestionType

/**
 * Mapper functions for converting between AiSuggestionEntity and AiSuggestion domain model
 */

fun AiSuggestionEntity.toDomain(): AiSuggestion = AiSuggestion(
    id = id,
    userId = userId,
    chatMessageId = chatMessageId,
    suggestionType = SuggestionType.fromString(suggestionType),
    suggestionData = suggestionData,
    status = SuggestionStatus.fromString(status),
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun AiSuggestion.toEntity(): AiSuggestionEntity = AiSuggestionEntity(
    id = id,
    userId = userId,
    chatMessageId = chatMessageId,
    suggestionType = suggestionType.toStorageString(),
    suggestionData = suggestionData,
    status = status.toStorageString(),
    createdAt = createdAt,
    updatedAt = updatedAt
)
