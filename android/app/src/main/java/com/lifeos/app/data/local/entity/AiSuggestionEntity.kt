package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "ai_suggestions")
data class AiSuggestionEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "chat_message_id")
    val chatMessageId: String,
    @ColumnInfo(name = "suggestion_type")
    val suggestionType: String, // note, action, principle, goal
    @ColumnInfo(name = "suggestion_data")
    val suggestionData: String, // JSON
    val status: String = "pending", // pending, approved, rejected, modified
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant
)
