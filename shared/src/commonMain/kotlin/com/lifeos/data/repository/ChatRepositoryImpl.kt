package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.ChatMessage
import com.lifeos.domain.repository.ChatRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val localDatabase: LocalDatabase
) : ChatRepository {

    override fun getChatMessages(userId: String): Flow<Result<List<ChatMessage>>> =
        localDatabase.getChatMessages(userId)
            .map<List<ChatMessage>, Result<List<ChatMessage>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getChatMessageById(id: String): Flow<Result<ChatMessage?>> =
        localDatabase.getChatMessageById(id)
            .map<ChatMessage?, Result<ChatMessage?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun saveChatMessage(message: ChatMessage): Result<Unit> = try {
        localDatabase.insertChatMessage(message)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteChatMessage(id: String): Result<Unit> = try {
        localDatabase.deleteChatMessage(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun clearChatHistory(userId: String): Result<Unit> = try {
        localDatabase.clearChatHistory(userId)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
