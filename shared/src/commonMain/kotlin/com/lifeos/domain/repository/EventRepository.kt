package com.lifeos.domain.repository

import com.lifeos.domain.model.Event
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

/**
 * Repository interface for Event operations.
 */
interface EventRepository {
    fun getEvents(userId: String): Flow<Result<List<Event>>>
    fun getEventsBetween(userId: String, startTime: Instant, endTime: Instant): Flow<Result<List<Event>>>
    fun getEventById(id: String): Flow<Result<Event?>>
    suspend fun createEvent(event: Event): Result<Unit>
    suspend fun updateEvent(event: Event): Result<Unit>
    suspend fun deleteEvent(id: String): Result<Unit>
}
