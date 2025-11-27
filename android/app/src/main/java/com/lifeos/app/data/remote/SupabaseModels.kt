package com.lifeos.app.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: String,
    val email: String? = null,
    @SerialName("display_name") val displayName: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("time_block_size") val timeBlockSize: Int = 10,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class FolderDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    val name: String,
    val color: Long? = null,
    @SerialName("is_default") val isDefault: Boolean = false,
    @SerialName("goal_id") val goalId: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class ActionDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("folder_id") val folderId: String,
    val title: String,
    @SerialName("due_date") val dueDate: String? = null,
    @SerialName("due_time") val dueTime: String? = null,
    @SerialName("is_recurring") val isRecurring: Boolean = false,
    @SerialName("recurrence_type") val recurrenceType: String? = null,
    @SerialName("notify_before") val notifyBefore: Int = 60,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class ActionCompletionDto(
    val id: String,
    @SerialName("action_id") val actionId: String,
    @SerialName("completed_date") val completedDate: String,
    @SerialName("completed_at") val completedAt: String
)

@Serializable
data class NotebookDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    val name: String,
    @SerialName("is_default") val isDefault: Boolean = false,
    @SerialName("goal_id") val goalId: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class NoteDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("notebook_id") val notebookId: String,
    val title: String,
    val content: String = "{}",
    @SerialName("is_journal") val isJournal: Boolean = false,
    @SerialName("journal_date") val journalDate: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class GoalDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("what_do_you_want") val whatDoYouWant: String,
    @SerialName("folder_id") val folderId: String? = null,
    @SerialName("notebook_id") val notebookId: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class PrincipleDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("parent_id") val parentId: String? = null,
    @SerialName("fundamental_truth") val fundamentalTruth: String,
    val experience: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class TimeBlockDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("action_id") val actionId: String,
    @SerialName("block_date") val blockDate: String,
    @SerialName("start_time") val startTime: String,
    @SerialName("end_time") val endTime: String,
    @SerialName("created_at") val createdAt: String
)

@Serializable
data class EventDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    val title: String,
    val description: String? = null,
    @SerialName("start_datetime") val startDatetime: String,
    @SerialName("end_datetime") val endDatetime: String,
    @SerialName("is_recurring") val isRecurring: Boolean = false,
    @SerialName("recurrence_type") val recurrenceType: String? = null,
    @SerialName("notify_before") val notifyBefore: Int = 30,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class ChatMessageDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    val role: String,
    val content: String,
    @SerialName("created_at") val createdAt: String
)

@Serializable
data class AiSuggestionDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    @SerialName("chat_message_id") val chatMessageId: String,
    @SerialName("suggestion_type") val suggestionType: String,
    @SerialName("suggestion_data") val suggestionData: String,
    val status: String = "pending",
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class DeviceTokenDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    val token: String,
    val platform: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)
