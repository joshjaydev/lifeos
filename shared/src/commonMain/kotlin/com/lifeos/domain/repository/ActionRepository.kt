package com.lifeos.domain.repository

import com.lifeos.domain.model.Action
import com.lifeos.domain.model.ActionCompletion
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Action operations.
 * Defines the contract for accessing action data from any data source.
 */
interface ActionRepository {
    fun getActions(userId: String): Flow<Result<List<Action>>>
    fun getActionsByFolder(folderId: String): Flow<Result<List<Action>>>
    fun getActionById(id: String): Flow<Result<Action?>>
    suspend fun createAction(action: Action): Result<Unit>
    suspend fun updateAction(action: Action): Result<Unit>
    suspend fun deleteAction(id: String): Result<Unit>

    // Action completion operations
    suspend fun completeAction(actionId: String, completion: ActionCompletion): Result<Unit>
    suspend fun getActionCompletions(actionId: String): Flow<Result<List<ActionCompletion>>>
}
