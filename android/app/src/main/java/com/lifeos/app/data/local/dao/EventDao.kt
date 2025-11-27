package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events WHERE user_id = :userId ORDER BY start_datetime ASC")
    fun getEvents(userId: String): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE user_id = :userId AND start_datetime >= :startTime AND start_datetime <= :endTime ORDER BY start_datetime ASC")
    fun getEventsBetween(userId: String, startTime: Long, endTime: Long): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getEvent(id: String): Flow<EventEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventEntity>)

    @Update
    suspend fun update(event: EventEntity)

    @Query("DELETE FROM events WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM events WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
