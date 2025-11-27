package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.AiSuggestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AiSuggestionDao {
    @Query("SELECT * FROM ai_suggestions WHERE user_id = :userId ORDER BY created_at DESC")
    fun getSuggestions(userId: String): Flow<List<AiSuggestionEntity>>

    @Query("SELECT * FROM ai_suggestions WHERE user_id = :userId AND status = 'PENDING' ORDER BY created_at DESC")
    fun getPendingSuggestions(userId: String): Flow<List<AiSuggestionEntity>>

    @Query("SELECT * FROM ai_suggestions WHERE chat_message_id = :messageId")
    fun getSuggestionsByMessage(messageId: String): Flow<List<AiSuggestionEntity>>

    @Query("SELECT * FROM ai_suggestions WHERE id = :id")
    fun getSuggestion(id: String): Flow<AiSuggestionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(suggestion: AiSuggestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(suggestions: List<AiSuggestionEntity>)

    @Update
    suspend fun update(suggestion: AiSuggestionEntity)

    @Query("DELETE FROM ai_suggestions WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM ai_suggestions WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
