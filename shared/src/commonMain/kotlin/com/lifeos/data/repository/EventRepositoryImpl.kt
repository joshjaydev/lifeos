package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Event
import com.lifeos.domain.repository.EventRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant

class EventRepositoryImpl(
    private val localDatabase: LocalDatabase
) : EventRepository {

    override fun getEvents(userId: String): Flow<Result<List<Event>>> =
        localDatabase.getEvents(userId)
            .map<List<Event>, Result<List<Event>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getEventsBetween(userId: String, startTime: Instant, endTime: Instant): Flow<Result<List<Event>>> =
        localDatabase.getEventsBetween(userId, startTime, endTime)
            .map<List<Event>, Result<List<Event>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getEventById(id: String): Flow<Result<Event?>> =
        localDatabase.getEventById(id)
            .map<Event?, Result<Event?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createEvent(event: Event): Result<Unit> = try {
        localDatabase.insertEvent(event)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateEvent(event: Event): Result<Unit> = try {
        localDatabase.updateEvent(event)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteEvent(id: String): Result<Unit> = try {
        localDatabase.deleteEvent(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
