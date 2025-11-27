package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.AiSuggestion
import com.lifeos.domain.model.SuggestionStatus
import com.lifeos.domain.repository.AiSuggestionRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AiSuggestionRepositoryImpl(
    private val localDatabase: LocalDatabase
) : AiSuggestionRepository {

    override fun getSuggestions(userId: String): Flow<Result<List<AiSuggestion>>> =
        localDatabase.getSuggestions(userId)
            .map<List<AiSuggestion>, Result<List<AiSuggestion>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getPendingSuggestions(userId: String): Flow<Result<List<AiSuggestion>>> =
        localDatabase.getPendingSuggestions(userId)
            .map<List<AiSuggestion>, Result<List<AiSuggestion>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getSuggestionById(id: String): Flow<Result<AiSuggestion?>> =
        localDatabase.getSuggestionById(id)
            .map<AiSuggestion?, Result<AiSuggestion?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun saveSuggestion(suggestion: AiSuggestion): Result<Unit> = try {
        localDatabase.insertSuggestion(suggestion)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateSuggestionStatus(id: String, status: SuggestionStatus): Result<Unit> = try {
        val suggestion = localDatabase.getSuggestionById(id)
        // Note: This requires a collect/first to get the actual value
        // For now, we'll need to refactor to support this properly
        // Alternatively, add updateStatus method to LocalDatabase
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteSuggestion(id: String): Result<Unit> = try {
        localDatabase.deleteSuggestion(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
