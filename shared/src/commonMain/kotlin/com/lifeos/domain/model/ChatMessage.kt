package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a chat message in the Atman AI conversation.
 */
data class ChatMessage(
    val id: String,
    val userId: String,
    val role: MessageRole,
    val content: String,
    val createdAt: Instant
)

/**
 * Enum representing the role of a message sender
 */
enum class MessageRole {
    USER,
    ASSISTANT;

    companion object {
        fun fromString(value: String): MessageRole = when (value.lowercase()) {
            "user" -> USER
            "assistant" -> ASSISTANT
            else -> USER
        }
    }

    fun toStorageString(): String = name.lowercase()
}
