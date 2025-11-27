package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Principle
import com.lifeos.domain.repository.PrincipleRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class PrincipleRepositoryImpl(
    private val localDatabase: LocalDatabase
) : PrincipleRepository {

    override fun getPrinciples(userId: String): Flow<Result<List<Principle>>> =
        localDatabase.getPrinciples(userId)
            .map<List<Principle>, Result<List<Principle>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getSubPrinciples(parentId: String): Flow<Result<List<Principle>>> =
        localDatabase.getSubPrinciples(parentId)
            .map<List<Principle>, Result<List<Principle>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getPrincipleById(id: String): Flow<Result<Principle?>> =
        localDatabase.getPrincipleById(id)
            .map<Principle?, Result<Principle?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createPrinciple(principle: Principle): Result<Unit> = try {
        localDatabase.insertPrinciple(principle)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updatePrinciple(principle: Principle): Result<Unit> = try {
        localDatabase.updatePrinciple(principle)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deletePrinciple(id: String): Result<Unit> = try {
        localDatabase.deletePrinciple(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
