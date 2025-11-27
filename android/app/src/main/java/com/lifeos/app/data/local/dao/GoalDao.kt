package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals WHERE user_id = :userId ORDER BY created_at DESC")
    fun getGoals(userId: String): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE id = :id")
    fun getGoal(id: String): Flow<GoalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(goal: GoalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(goals: List<GoalEntity>)

    @Update
    suspend fun update(goal: GoalEntity)

    @Query("DELETE FROM goals WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM goals WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
