package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.ActionCompletion
import com.lifeos.domain.repository.ActionRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Implementation of ActionRepository.
 * This class is in commonMain and can be shared across all platforms.
 */
class ActionRepositoryImpl(
    private val localDatabase: LocalDatabase
) : ActionRepository {

    override fun getActions(userId: String): Flow<Result<List<Action>>> =
        localDatabase.getActions(userId)
            .map<List<Action>, Result<List<Action>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getActionsByFolder(folderId: String): Flow<Result<List<Action>>> =
        localDatabase.getActionsByFolder(folderId)
            .map<List<Action>, Result<List<Action>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getActionById(id: String): Flow<Result<Action?>> =
        localDatabase.getActionById(id)
            .map<Action?, Result<Action?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createAction(action: Action): Result<Unit> = try {
        localDatabase.insertAction(action)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateAction(action: Action): Result<Unit> = try {
        localDatabase.updateAction(action)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteAction(id: String): Result<Unit> = try {
        localDatabase.deleteAction(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun completeAction(actionId: String, completion: ActionCompletion): Result<Unit> = try {
        localDatabase.insertActionCompletion(completion)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getActionCompletions(actionId: String): Flow<Result<List<ActionCompletion>>> =
        localDatabase.getActionCompletions(actionId)
            .map<List<ActionCompletion>, Result<List<ActionCompletion>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }
}
