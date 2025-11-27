package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Goal
import com.lifeos.domain.repository.GoalRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val localDatabase: LocalDatabase
) : GoalRepository {

    override fun getGoals(userId: String): Flow<Result<List<Goal>>> =
        localDatabase.getGoals(userId)
            .map<List<Goal>, Result<List<Goal>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getGoalById(id: String): Flow<Result<Goal?>> =
        localDatabase.getGoalById(id)
            .map<Goal?, Result<Goal?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createGoal(goal: Goal): Result<Unit> = try {
        localDatabase.insertGoal(goal)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateGoal(goal: Goal): Result<Unit> = try {
        localDatabase.updateGoal(goal)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteGoal(id: String): Result<Unit> = try {
        localDatabase.deleteGoal(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
