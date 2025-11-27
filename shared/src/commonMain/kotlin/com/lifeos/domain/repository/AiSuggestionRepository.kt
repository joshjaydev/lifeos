package com.lifeos.domain.repository

import com.lifeos.domain.model.AiSuggestion
import com.lifeos.domain.model.SuggestionStatus
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for AI Suggestion operations.
 */
interface AiSuggestionRepository {
    fun getSuggestions(userId: String): Flow<Result<List<AiSuggestion>>>
    fun getPendingSuggestions(userId: String): Flow<Result<List<AiSuggestion>>>
    fun getSuggestionById(id: String): Flow<Result<AiSuggestion?>>
    suspend fun saveSuggestion(suggestion: AiSuggestion): Result<Unit>
    suspend fun updateSuggestionStatus(id: String, status: SuggestionStatus): Result<Unit>
    suspend fun deleteSuggestion(id: String): Result<Unit>
}
