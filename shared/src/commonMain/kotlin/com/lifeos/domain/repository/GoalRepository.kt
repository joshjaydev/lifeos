package com.lifeos.domain.repository

import com.lifeos.domain.model.Goal
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Goal operations.
 */
interface GoalRepository {
    fun getGoals(userId: String): Flow<Result<List<Goal>>>
    fun getGoalById(id: String): Flow<Result<Goal?>>
    suspend fun createGoal(goal: Goal): Result<Unit>
    suspend fun updateGoal(goal: Goal): Result<Unit>
    suspend fun deleteGoal(id: String): Result<Unit>
}
