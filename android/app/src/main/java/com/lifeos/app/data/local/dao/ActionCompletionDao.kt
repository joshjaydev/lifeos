package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.ActionCompletionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionCompletionDao {
    @Query("SELECT * FROM action_completions WHERE action_id = :actionId")
    fun getCompletions(actionId: String): Flow<List<ActionCompletionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(completion: ActionCompletionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(completions: List<ActionCompletionEntity>)

    @Query("DELETE FROM action_completions WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM action_completions WHERE action_id = :actionId")
    suspend fun deleteByAction(actionId: String)
}
