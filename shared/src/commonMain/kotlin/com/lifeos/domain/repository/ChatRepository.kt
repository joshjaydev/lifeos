package com.lifeos.domain.repository

import com.lifeos.domain.model.ChatMessage
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Chat operations.
 */
interface ChatRepository {
    fun getChatMessages(userId: String): Flow<Result<List<ChatMessage>>>
    fun getChatMessageById(id: String): Flow<Result<ChatMessage?>>
    suspend fun saveChatMessage(message: ChatMessage): Result<Unit>
    suspend fun deleteChatMessage(id: String): Result<Unit>
    suspend fun clearChatHistory(userId: String): Result<Unit>
}
