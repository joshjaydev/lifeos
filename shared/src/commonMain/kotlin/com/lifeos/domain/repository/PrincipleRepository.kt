package com.lifeos.domain.repository

import com.lifeos.domain.model.Principle
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Principle operations.
 */
interface PrincipleRepository {
    fun getPrinciples(userId: String): Flow<Result<List<Principle>>>
    fun getSubPrinciples(parentId: String): Flow<Result<List<Principle>>>
    fun getPrincipleById(id: String): Flow<Result<Principle?>>
    suspend fun createPrinciple(principle: Principle): Result<Unit>
    suspend fun updatePrinciple(principle: Principle): Result<Unit>
    suspend fun deletePrinciple(id: String): Result<Unit>
}
