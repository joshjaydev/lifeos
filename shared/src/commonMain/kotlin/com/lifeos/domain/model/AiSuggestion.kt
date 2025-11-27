package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing an AI-generated suggestion that needs user approval.
 */
data class AiSuggestion(
    val id: String,
    val userId: String,
    val chatMessageId: String,
    val suggestionType: SuggestionType,
    val suggestionData: String, // JSON data for the suggestion
    val status: SuggestionStatus = SuggestionStatus.PENDING,
    val createdAt: Instant,
    val updatedAt: Instant
)

/**
 * Enum representing the type of suggestion
 */
enum class SuggestionType {
    NOTE,
    ACTION,
    PRINCIPLE,
    GOAL,
    EVENT,
    TIME_BLOCK;

    companion object {
        fun fromString(value: String): SuggestionType = when (value.lowercase()) {
            "note" -> NOTE
            "action" -> ACTION
            "principle" -> PRINCIPLE
            "goal" -> GOAL
            "event" -> EVENT
            "timeblock", "time_block" -> TIME_BLOCK
            else -> ACTION
        }
    }

    fun toStorageString(): String = name.lowercase().replace("_", "")
}

/**
 * Enum representing the status of a suggestion
 */
enum class SuggestionStatus {
    PENDING,
    APPROVED,
    REJECTED,
    MODIFIED;

    companion object {
        fun fromString(value: String): SuggestionStatus = when (value.lowercase()) {
            "pending" -> PENDING
            "approved" -> APPROVED
            "rejected" -> REJECTED
            "modified" -> MODIFIED
            else -> PENDING
        }
    }

    fun toStorageString(): String = name.lowercase()
}
