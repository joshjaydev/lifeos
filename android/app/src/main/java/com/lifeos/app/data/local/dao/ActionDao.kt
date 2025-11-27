package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.ActionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {
    @Query("SELECT * FROM actions WHERE user_id = :userId ORDER BY due_date ASC")
    fun getActions(userId: String): Flow<List<ActionEntity>>

    @Query("SELECT * FROM actions WHERE folder_id = :folderId ORDER BY due_date ASC")
    fun getActionsByFolder(folderId: String): Flow<List<ActionEntity>>

    @Query("SELECT * FROM actions WHERE id = :id")
    fun getAction(id: String): Flow<ActionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(action: ActionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actions: List<ActionEntity>)

    @Update
    suspend fun update(action: ActionEntity)

    @Query("DELETE FROM actions WHERE folder_id = :folderId")
    suspend fun deleteByFolder(folderId: String)

    @Query("DELETE FROM actions WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM actions WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
