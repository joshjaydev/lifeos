package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.NotebookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookDao {
    @Query("SELECT * FROM notebooks WHERE user_id = :userId ORDER BY is_default DESC, name ASC")
    fun getNotebooks(userId: String): Flow<List<NotebookEntity>>

    @Query("SELECT * FROM notebooks WHERE id = :id")
    fun getNotebook(id: String): Flow<NotebookEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notebook: NotebookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notebooks: List<NotebookEntity>)

    @Update
    suspend fun update(notebook: NotebookEntity)

    @Query("DELETE FROM notebooks WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM notebooks WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
