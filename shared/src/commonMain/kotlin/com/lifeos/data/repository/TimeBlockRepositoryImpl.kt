package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.TimeBlock
import com.lifeos.domain.repository.TimeBlockRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class TimeBlockRepositoryImpl(
    private val localDatabase: LocalDatabase
) : TimeBlockRepository {

    override fun getTimeBlocks(userId: String, date: LocalDate): Flow<Result<List<TimeBlock>>> =
        localDatabase.getTimeBlocks(userId, date)
            .map<List<TimeBlock>, Result<List<TimeBlock>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getAllTimeBlocks(userId: String): Flow<Result<List<TimeBlock>>> =
        localDatabase.getAllTimeBlocks(userId)
            .map<List<TimeBlock>, Result<List<TimeBlock>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getTimeBlockById(id: String): Flow<Result<TimeBlock?>> =
        localDatabase.getTimeBlockById(id)
            .map<TimeBlock?, Result<TimeBlock?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createTimeBlock(timeBlock: TimeBlock): Result<Unit> = try {
        localDatabase.insertTimeBlock(timeBlock)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateTimeBlock(timeBlock: TimeBlock): Result<Unit> = try {
        localDatabase.updateTimeBlock(timeBlock)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteTimeBlock(id: String): Result<Unit> = try {
        localDatabase.deleteTimeBlock(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
