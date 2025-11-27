package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages WHERE user_id = :userId ORDER BY created_at ASC")
    fun getMessages(userId: String): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM chat_messages WHERE id = :id")
    fun getMessage(id: String): Flow<ChatMessageEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: ChatMessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<ChatMessageEntity>)

    @Query("DELETE FROM chat_messages WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM chat_messages WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
