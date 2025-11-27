package com.lifeos.domain.repository

import com.lifeos.domain.model.TimeBlock
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

/**
 * Repository interface for TimeBlock operations.
 */
interface TimeBlockRepository {
    fun getTimeBlocks(userId: String, date: LocalDate): Flow<Result<List<TimeBlock>>>
    fun getAllTimeBlocks(userId: String): Flow<Result<List<TimeBlock>>>
    fun getTimeBlockById(id: String): Flow<Result<TimeBlock?>>
    suspend fun createTimeBlock(timeBlock: TimeBlock): Result<Unit>
    suspend fun updateTimeBlock(timeBlock: TimeBlock): Result<Unit>
    suspend fun deleteTimeBlock(id: String): Result<Unit>
}
