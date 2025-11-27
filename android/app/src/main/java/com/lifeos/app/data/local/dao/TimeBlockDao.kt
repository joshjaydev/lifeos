package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.TimeBlockEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeBlockDao {
    @Query("SELECT * FROM time_blocks WHERE user_id = :userId AND block_date = :date ORDER BY start_time ASC")
    fun getTimeBlocks(userId: String, date: String): Flow<List<TimeBlockEntity>>

    @Query("SELECT * FROM time_blocks WHERE user_id = :userId ORDER BY block_date ASC, start_time ASC")
    fun getAllTimeBlocks(userId: String): Flow<List<TimeBlockEntity>>

    @Query("SELECT * FROM time_blocks WHERE id = :id")
    fun getTimeBlock(id: String): Flow<TimeBlockEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timeBlock: TimeBlockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(timeBlocks: List<TimeBlockEntity>)

    @Update
    suspend fun update(timeBlock: TimeBlockEntity)

    @Query("DELETE FROM time_blocks WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM time_blocks WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
